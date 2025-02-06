package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.client.MeasurementClient;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.dto.defectMeasurement.NewDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defectMeasurement.ResponseDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defectMeasurement.UpdateDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.measuredParameter.NewMeasuredParameterDto;
import ru.nabokovsg.measurement.dto.measuredParameter.UpdateMeasuredParameterDto;
import ru.nabokovsg.measurement.dto.defectMeasurement.ResponseShortDefectMeasurementDto;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.diagnostics.DefectMeasurementMapper;
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
public class DefectMeasurementServiceImpl implements DefectMeasurementService {

    private final DefectMeasurementRepository repository;
    private final DefectMeasurementMapper mapper;
    private final MeasuredParameterService measuredParameterService;
    private final DefectLibraryService libraryService;
    private final MeasurementDuplicateService duplicateService;
    private final CalculationDefectMeasurementService calculationService;
    private final MeasurementClient client;
    private final ConvertingMeasuredParameterToStringService stringBuilder;

    @Override
    public ResponseShortDefectMeasurementDto save(NewDefectMeasurementDto defectDto) {
        Set<DefectMeasurement> defects = getAllByPredicate(defectDto.getEquipmentId()
                , defectDto.getElementId()
                , defectDto.getPartElementId()
                , defectDto.getDefectLibraryId());
        Map<Long, Double> parameters = defectDto.getMeasuredParameters()
                                            .stream()
                                            .collect(Collectors.toMap(NewMeasuredParameterDto::getParameterLibraryId
                                                                    , NewMeasuredParameterDto::getValue));
        DefectMeasurement defect = Objects.requireNonNullElse(
                                                 duplicateService.searchDefectMeasurementDuplicate(defects, parameters)
                                               , create(defectDto));
        if (defect.getId() == null) {
            defect = repository.save(defect);
            measuredParameterService.save(new ParameterMeasurementBuilder.Builder()
                                                                         .libraryDataType(MeasurementType.DEFECT)
                                                                         .defect(defect)
                                                                         .build());
            defects.add(defect);
        } else {
            measuredParameterService.updateDuplicate(defect.getMeasuredParameters());
            defect = repository.save(defect);
        }
        calculationService.calculationCalculationDefectManager(defect, defects);
        return mapper.mapToResponseShortDefectMeasurementDto(defect);
    }

    @Override
    public ResponseShortDefectMeasurementDto update(UpdateDefectMeasurementDto defectDto) {
        DefectMeasurement defect = getById(defectDto.getId());
        Set<DefectMeasurement> defects = getAllByPredicate(defect.getEquipmentId()
                                                         , defect.getElementId()
                                                         , defect.getPartElementId()
                                                         , defect.getDefectLibraryId());
        Map<Long, Double> parameters = setParameterLibraryId(defect.getMeasuredParameters(),defectDto);
        DefectMeasurement duplicate = duplicateService.searchDefectMeasurementDuplicate(defects, parameters);
        if (duplicate == null || duplicate.getId().equals(defectDto.getId())) {
            mapper.mapToParametersString(defect
                    , stringBuilder.convertMeasuredParameter(
                            measuredParameterService.update(defect.getMeasuredParameters()
                                    , defectDto.getMeasuredParameters())));
            defect = repository.save(defect);
        } else {
            deleteDefect(defect);
            defects.remove(defect);
            measuredParameterService.updateDuplicate(duplicate.getMeasuredParameters());
            defect = repository.save(duplicate);
        }
        calculationService.calculationCalculationDefectManager(defect, defects);
        return mapper.mapToResponseShortDefectMeasurementDto(defect);
    }

    @Override
    public ResponseDefectMeasurementDto get(Long id) {
        return mapper.mapToResponseDefectMeasurementDto(getById(id));
    }

    @Override
    public List<ResponseShortDefectMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId) {
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
                .map(mapper::mapToResponseShortDefectMeasurementDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        DefectMeasurement defect = getById(id);
        Set<DefectMeasurement> defects = getAllByPredicate(defect.getEquipmentId()
                                                         , defect.getElementId()
                                                         , defect.getPartElementId()
                                                         , defect.getDefectLibraryId());
        defects.remove(defect);
        deleteDefect(defect);
        calculationService.deleteCalculationDefectManager(defect, defects);
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

    private Map<Long, Double> setParameterLibraryId(Set<MeasuredParameter> measuredParameters
            , UpdateDefectMeasurementDto defectDto) {
        Map<Long, Double> parameters = defectDto.getMeasuredParameters()
                .stream()
                .collect(Collectors.toMap(UpdateMeasuredParameterDto::getId
                        , UpdateMeasuredParameterDto::getValue));
        return measuredParameters.stream()
                .collect(Collectors.toMap(MeasuredParameter::getParameterId
                        , parameter -> parameters.get(parameter.getId())));
    }

    private void deleteDefect(DefectMeasurement defect) {
        measuredParameterService.deleteAll(defect.getMeasuredParameters());
        repository.deleteById(defect.getId());
    }

    private DefectMeasurement getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Defect measurement with id=%s not found", id)));
    }

    private Set<DefectMeasurement> getAllByPredicate(Long equipmentId, Long elementId, Long partElementId, Long defectLibraryId) {
        if (partElementId != null) {
            return repository.findAllByEquipmentIdAndElementIdAndPartElementIdAndDefectLibraryId(
                    equipmentId
                    , elementId
                    , partElementId
                    , defectLibraryId);
        } else {
            return repository.findAllByEquipmentIdAndElementIdAndDefectLibraryId(equipmentId
                    , elementId
                    , defectLibraryId);
        }
    }
}