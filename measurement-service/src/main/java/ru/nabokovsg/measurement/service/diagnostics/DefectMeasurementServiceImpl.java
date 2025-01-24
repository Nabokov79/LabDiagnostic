package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.client.MeasurementClient;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.dto.defect.NewDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defect.ResponseDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defect.UpdateDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.measuredParameter.NewMeasuredParameterDto;
import ru.nabokovsg.measurement.dto.measuredParameter.UpdateMeasuredParameterDto;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.diagnostics.DefectMeasurementMapper;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.common.MeasurementType;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.ParameterMeasurementBuilder;
import ru.nabokovsg.measurement.model.library.DefectLibrary;
import ru.nabokovsg.measurement.repository.diagnostics.DefectMeasurementRepository;
import ru.nabokovsg.measurement.service.common.ConvertingMeasuredParameterToStringService;
import ru.nabokovsg.measurement.service.library.DefectLibraryService;
import ru.nabokovsg.measurement.сalculation.CalculationDefectMeasurementService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefectMeasurementServiceImpl implements DefectMeasurementService {

    private final DefectMeasurementRepository repository;
    private final DefectMeasurementMapper mapper;
    private final MeasuredParameterService measuredParameterService;
    private final DefectLibraryService libraryService;
    private final CalculationDefectMeasurementService calculationService;
    private final MeasurementClient client;
    private final ConvertingMeasuredParameterToStringService stringBuilder;

    @Override
    public ResponseDefectMeasurementDto save(NewDefectMeasurementDto defectDto) {
        DefectMeasurement defect = create(defectDto);
        Set<DefectMeasurement> defects = getAllByPredicate(defect);
        Map<Long, Double> parameters = defectDto.getMeasuredParameters()
                                                .stream()
                                                .collect(Collectors.toMap(NewMeasuredParameterDto::getParameterLibraryId
                                                                        , NewMeasuredParameterDto::getValue));
        defect = getDuplicate(defect, defects, parameters);
        if (defect.getId() == null) {
            defect = repository.save(defect);
            defects.add(defect);
            setMeasuredParameters(defect, defect.getMeasuredParameters());
            calculationService.save(defect, defects, defect.getCalculation());
        }
        return mapper.mapToResponseDefectMeasurementDto(defect);
    }

    private DefectMeasurement create(NewDefectMeasurementDto defectDto) {
        DefectLibrary defectLibrary = libraryService.getById(defectDto.getDefectLibraryId());
        EquipmentDto equipment = client.getEquipment(defectDto.getElementId(), defectDto.getPartElementId());
        Set<MeasuredParameter> parameters = measuredParameterService.create(defectDto.getMeasuredParameters()
                                                                          , defectLibrary.getMeasuredParameters());
        return mapper.mapToDefectMeasurement(defectDto
                                           , parameters
                                           , defectLibrary
                                           , equipment
                                           , stringBuilder.convertMeasuredParameter(parameters));

    }

    @Override
    public ResponseDefectMeasurementDto update(UpdateDefectMeasurementDto defectDto) {
        DefectMeasurement defect = getById(defectDto.getId());
        Set<DefectMeasurement> defects = getAllByPredicate(defect);
        Map<Long, Double> parameters = defectDto.getMeasuredParameters()
                .stream()
                .collect(Collectors.toMap(UpdateMeasuredParameterDto::getId
                        , UpdateMeasuredParameterDto::getValue));
        replaceParameters(defect.getMeasuredParameters(), parameters);
        defect = getDuplicate(defect, defects, parameters);
        if (defect.getId().equals(defectDto.getId())) {
            mapper.mapToParametersString(defect
                    , stringBuilder.convertMeasuredParameter(
                            measuredParameterService.update(defect.getMeasuredParameters()
                                    , defectDto.getMeasuredParameters())));
            calculationService.save(defect, defects, defect.getCalculation());
        }
        return mapper.mapToResponseDefectMeasurementDto(repository.save(defect));
    }

    @Override
    public ResponseDefectMeasurementDto get(Long id) {
        return mapper.mapToResponseDefectMeasurementDto(getById(id));
    }

    @Override
    public List<ResponseDefectMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId) {
        Set<DefectMeasurement> defectMeasurements = new HashSet<>();
        if (equipmentId != null) {
            if (elementId != null) {
                if (partElementId != null) {
                    defectMeasurements.addAll(repository.findAllByEquipmentIdAndElementIdAndPartElementId(equipmentId
                            , elementId
                            , partElementId));
                }
                defectMeasurements.addAll(repository.findAllByEquipmentIdAndElementId(equipmentId, elementId));
            }
            defectMeasurements.addAll(repository.findAllByEquipmentId(equipmentId));
        }
        return defectMeasurements.stream()
                .map(mapper::mapToResponseDefectMeasurementDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        DefectMeasurement defect = getById(id);
        repository.deleteById(id);
        calculationService.delete(defect, getAllByPredicate(defect), defect.getCalculation());
    }

    private DefectMeasurement getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Defect measurement with id=%s not found", id)));
    }

    private Set<DefectMeasurement> getAllByPredicate(DefectMeasurement defect) {
        if (defect.getPartElementId() != null) {
            return repository.findAllByEquipmentIdAndElementIdAndPartElementIdAndDefectLibraryId(
                      defect.getEquipmentId()
                    , defect.getElementId()
                    , defect.getPartElementId()
                    , defect.getDefectLibraryId());
        } else {
            return repository.findAllByEquipmentIdAndElementIdAndDefectLibraryId(defect.getEquipmentId()
                    , defect.getElementId()
                    , defect.getDefectLibraryId());
        }
    }

    private DefectMeasurement getDuplicate(DefectMeasurement defect
                                         , Set<DefectMeasurement> defects
                                         , Map<Long, Double> parameters) {
            for (DefectMeasurement duplicate : defects) {
                log.info("Defect: {}", defect);
                log.info("Duplicate: {}", duplicate);
            if (measuredParameterService.compare(duplicate.getMeasuredParameters(), parameters)) {
                log.info("Duplicate found");
                log.info(" ");
                return updateDuplicate(duplicate, defects, parameters);
            }
        }
        return defect;
    }

    private DefectMeasurement updateDuplicate(DefectMeasurement defect
            , Set<DefectMeasurement> defects
            , Map<Long, Double> parameters) {
        sumQuantityParameter(defect, parameters);
        measuredParameterService.updateDuplicate(defect.getMeasuredParameters());
        mapper.mapToParametersString(defect
                , stringBuilder.convertMeasuredParameter(defect.getMeasuredParameters()));
        calculationService.save(defect, defects, defect.getCalculation());
        defects.remove(defect);
        delete(defect.getId());
        return repository.save(defect);
    }

    private void sumQuantityParameter(DefectMeasurement defect, Map<Long, Double> parameters) {
        defect.getMeasuredParameters()
                .forEach(parameter -> {
                    if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                        parameter.setValue(parameter.getValue() + parameters.get(parameter.getParameterId()));
                    }
                });
    }

    private void setMeasuredParameters(DefectMeasurement defect, Set<MeasuredParameter> parameters) {
        defect.setMeasuredParameters(measuredParameterService.save(
                new ParameterMeasurementBuilder.Builder()
                        .libraryDataType(MeasurementType.DEFECT)
                        .defect(defect)
                        .measuredParameters(parameters)
                        .build()));
    }

    private void replaceParameters(Set<MeasuredParameter> measuredParameters
            , Map<Long, Double> parameters) {
        measuredParameters.forEach(parameter -> {
            if (!Objects.equals(parameter.getId(), parameter.getParameterId())) {
                parameters.remove(parameter.getId());
            }
            parameters.put(parameter.getParameterId(), parameters.get(parameter.getId()));

        });
    }
}