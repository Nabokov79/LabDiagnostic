package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
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
    private final CalculationDefectMeasurementService calculationService;
    private final MeasurementClient client;
    private final ConvertingMeasuredParameterToStringService stringBuilder;

    @Override
    public ResponseDefectMeasurementDto save(NewDefectMeasurementDto defectDto) {
        Set<DefectMeasurement> defects = getAllByPredicate(defectDto.getEquipmentId()
                                                         , defectDto.getElementId()
                                                         , defectDto.getPartElementId()
                                                         , defectDto.getDefectLibraryId());
        Map<Long, Double> parameters = defectDto.getMeasuredParameters()
                                                .stream()
                                                .collect(Collectors.toMap(NewMeasuredParameterDto::getParameterLibraryId
                                                                        , NewMeasuredParameterDto::getValue));
        DefectMeasurement defect = getDuplicate(create(defectDto), defects, parameters);
        if (defect.getId() != null) {
            measuredParameterService.updateDuplicate(defect.getMeasuredParameters());
            defect = repository.save(defect);
        } else {
            defect = repository.save(defect);
            measuredParameterService.save(getParameterMeasurementBuilder(defect));
            defects.add(defect);
        }
        calculationService.save(defect, defects);
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
        Set<DefectMeasurement> defects = getAllByPredicate(defect.getEquipmentId()
                                                         , defect.getElementId()
                                                         , defect.getPartElementId()
                                                         , defect.getDefectLibraryId());
        Map<Long, Double> parameters = defectDto.getMeasuredParameters()
                                                .stream()
                                                .collect(Collectors.toMap(UpdateMeasuredParameterDto::getId
                                                                        , UpdateMeasuredParameterDto::getValue));
        DefectMeasurement duplicate = getDuplicate(defect, defects, parameters);
        if (duplicate.getId().equals(defectDto.getId())) {
            mapper.mapToParametersString(defect
                    , stringBuilder.convertMeasuredParameter(
                            measuredParameterService.update(defect.getMeasuredParameters()
                                                          , defectDto.getMeasuredParameters())));
            calculationService.save(defect, defects);
            return mapper.mapToResponseDefectMeasurementDto(repository.save(defect));
        } else {
            measuredParameterService.updateDuplicate(duplicate.getMeasuredParameters());
            deleteDefect(defect, defects);
            calculationService.save(defect, defects);
            return mapper.mapToResponseDefectMeasurementDto(repository.save(duplicate));
        }
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
        deleteDefect(defect, getAllByPredicate(defect.getEquipmentId()
                                             , defect.getElementId()
                                             , defect.getPartElementId()
                                             , defect.getDefectLibraryId()));
    }

    public void deleteDefect(DefectMeasurement defect, Set<DefectMeasurement> defects) {
        measuredParameterService.deleteAll(defect.getMeasuredParameters());
        repository.deleteById(defect.getId());
        if (!defects.isEmpty()) {
            defects.remove(defect);
        }
        calculationService.delete(defect, defects);
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
                    , partElementId);
        }
    }

    private DefectMeasurement getDuplicate(DefectMeasurement defect, Set<DefectMeasurement> defects, Map<Long, Double> parameters) {
        if (defect.getId() != null) {
            defect.getMeasuredParameters().forEach(parameter -> {
                parameters.put(parameter.getParameterId(), parameters.get(parameter.getId()));
                if(!parameter.getId().equals(parameter.getParameterId())) {
                    parameters.remove(parameter.getId());
                }
            });
        }
        if (!defects.isEmpty()) {
            for (DefectMeasurement duplicate : defects) {
                if (measuredParameterService.compare(duplicate.getMeasuredParameters(), parameters)) {
                    mapper.mapToParametersString(duplicate, stringBuilder.convertMeasuredParameter(duplicate.getMeasuredParameters()));
                    if (!duplicate.getId().equals(defect.getId())) {
                        defects.remove(defect);
                    }
                    return duplicate;
                }
            }
        }
        return defect;
    }

    private ParameterMeasurementBuilder getParameterMeasurementBuilder(DefectMeasurement defect) {
        return new ParameterMeasurementBuilder.Builder()
                                              .libraryDataType(MeasurementType.DEFECT)
                                              .defect(defect)
                                              .build();
    }
}