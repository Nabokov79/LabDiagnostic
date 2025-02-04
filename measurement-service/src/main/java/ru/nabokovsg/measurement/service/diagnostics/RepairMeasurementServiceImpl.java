package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.client.MeasurementClient;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.dto.measuredParameter.NewMeasuredParameterDto;
import ru.nabokovsg.measurement.dto.measuredParameter.UpdateMeasuredParameterDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.NewRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.ResponseRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.UpdateRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.ResponseShortRepairMeasurementDto;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.diagnostics.RepairMeasurementMapper;
import ru.nabokovsg.measurement.model.common.MeasurementType;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.ParameterMeasurementBuilder;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;
import ru.nabokovsg.measurement.model.library.RepairLibrary;
import ru.nabokovsg.measurement.repository.diagnostics.RepairMeasurementRepository;
import ru.nabokovsg.measurement.service.common.ConvertingMeasuredParameterToStringService;
import ru.nabokovsg.measurement.service.library.RepairLibraryService;
import ru.nabokovsg.measurement.сalculation.CalculationRepairMeasurementService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepairMeasurementServiceImpl implements RepairMeasurementService {

    private final RepairMeasurementRepository repository;
    private final RepairMeasurementMapper mapper;
    private final RepairLibraryService libraryService;
    private final MeasuredParameterService measuredParameterService;
    private final CalculationRepairMeasurementService calculationService;
    private final MeasurementClient client;
    private final ConvertingMeasuredParameterToStringService stringBuilder;

    @Override
    public ResponseShortRepairMeasurementDto save(NewRepairMeasurementDto repairDto) {
        Set<RepairMeasurement> repairs = getAllByPredicate(repairDto.getEquipmentId()
                , repairDto.getElementId()
                , repairDto.getPartElementId()
                , repairDto.getRepairLibraryId());
        Map<Long, Double> parameters = repairDto.getMeasuredParameters()
                .stream()
                .collect(Collectors.toMap(NewMeasuredParameterDto::getParameterLibraryId
                        , NewMeasuredParameterDto::getValue));
        RepairMeasurement repair = getDuplicate(create(repairDto), repairs, parameters);
        if (repair.getId() == null) {
            repair = repository.save(repair);
            measuredParameterService.save(getParameterMeasurementBuilder(repair));
            repairs.add(repair);
        }
        calculationCalculationRepairManager(repair, repairs);
        return mapper.mapToResponseShortRepairMeasurementDto(repair);
    }

    @Override
    public ResponseShortRepairMeasurementDto update(UpdateRepairMeasurementDto repairDto) {
        RepairMeasurement repair = getById(repairDto.getId());
        Set<RepairMeasurement> repairs = getAllByPredicate(repair.getEquipmentId()
                , repair.getElementId()
                , repair.getPartElementId()
                , repair.getRepairLibraryId());
        Map<Long, Double> parameters = repairDto.getMeasuredParameters()
                .stream()
                .collect(Collectors.toMap(UpdateMeasuredParameterDto::getId
                        , UpdateMeasuredParameterDto::getValue));
        repair = getDuplicate(repair, repairs, parameters);
        if (repair.getId().equals(repairDto.getId())) {
            mapper.mapToParametersString(repair
                    , stringBuilder.convertMeasuredParameter(
                            measuredParameterService.update(repair.getMeasuredParameters()
                                    , repairDto.getMeasuredParameters())));
            repair = repository.save(repair);
            calculationCalculationRepairManager(repair, repairs);
        }
        return mapper.mapToResponseShortRepairMeasurementDto(repair);
    }

    @Override
    public ResponseRepairMeasurementDto get(Long id) {
        return mapper.mapToResponseRepairMeasurementDto(getById(id));
    }

    @Override
    public List<ResponseShortRepairMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId) {
        Set<RepairMeasurement> repairMeasurements = new HashSet<>();
        if (equipmentId != null) {
            if (elementId != null) {
                if (partElementId != null) {
                    repairMeasurements.addAll(repository.findAllByEquipmentIdAndElementIdAndPartElementId(equipmentId
                            , elementId
                            , partElementId));
                }
                repairMeasurements.addAll(repository.findAllByEquipmentIdAndElementId(equipmentId, elementId));
            }
            repairMeasurements.addAll(repository.findAllByEquipmentId(equipmentId));
        }
        return repairMeasurements.stream()
                                 .map(mapper::mapToResponseShortRepairMeasurementDto)
                                 .toList();
    }

    @Override
    public void delete(Long id) {
        RepairMeasurement repair = getById(id);
        deleteCalculationRepairManager(repair, getAllByPredicate(repair.getEquipmentId()
                                                               , repair.getElementId()
                                                               , repair.getPartElementId()
                                                               , repair.getRepairLibraryId()));
    }

    private RepairMeasurement create(NewRepairMeasurementDto repairDto) {
        RepairLibrary repairLibrary = libraryService.getById(repairDto.getRepairLibraryId());
        EquipmentDto equipment = client.getEquipment(repairDto.getElementId(), repairDto.getPartElementId());
        Set<MeasuredParameter> parameters = measuredParameterService.create(repairDto.getMeasuredParameters()
                                                                          , repairLibrary.getMeasuredParameters());
        return mapper.mapToRepairMeasurement(repairDto
                                           , parameters
                                           , repairLibrary
                                           , equipment
                                           , stringBuilder.convertMeasuredParameter(parameters));
    }

    private RepairMeasurement getDuplicate(RepairMeasurement repair, Set<RepairMeasurement> repairs, Map<Long, Double> parameters) {
        parameters = replaceParameters(repair, parameters);
        if (!repairs.isEmpty()) {
            for (RepairMeasurement duplicate : repairs) {
                if (measuredParameterService.compare(duplicate.getMeasuredParameters(), parameters)) {
                    mapper.mapToParametersString(duplicate, stringBuilder.convertMeasuredParameter(duplicate.getMeasuredParameters()));
                    if (!duplicate.getId().equals(repair.getId())) {
                        repairs.remove(repair);
                    }
                    if (repair.getId() == null || repair.getId().equals(duplicate.getId())) {
                        return updateDuplicate(false, repair, duplicate, repairs);
                    } else {
                        return updateDuplicate(true, repair, duplicate, repairs);
                    }
                }
            }
        }
        return repair;
    }

    private RepairMeasurement updateDuplicate(boolean update, RepairMeasurement repair
                                            , RepairMeasurement duplicate, Set<RepairMeasurement> repairs) {
        measuredParameterService.updateDuplicate(duplicate.getMeasuredParameters());
        if (update) {
            deleteRepair(repair);
            deleteCalculationRepairManager(repair, repairs);
        }
        return repository.save(duplicate);
    }

    public void calculationCalculationRepairManager(RepairMeasurement repair, Set<RepairMeasurement> repairs) {
        switch (repair.getCalculation()) {
            case MIN, MAX, MAX_MIN -> calculationService.saveCalculationMinMax(repair, repairs);
            case NO_ACTION -> calculationService.saveWithoutCalculation(repair);
        }
    }

    public void deleteCalculationRepairManager(RepairMeasurement repair, Set<RepairMeasurement> repairs) {
        repairs.remove(repair);
        deleteRepair(repair);
        switch (repair.getCalculation()) {
            case MIN, MAX, MAX_MIN -> {
                if (repairs.isEmpty()) {
                    calculationService.delete(repair);
                } else {
                    calculationCalculationRepairManager(repair, repairs);
                }
            }
            case NO_ACTION -> calculationService.deleteByDefectId(repair);
        }
    }

    private Map<Long, Double> replaceParameters(RepairMeasurement repair, Map<Long, Double> parameters) {
        if (repair.getId() != null) {
            return repair.getMeasuredParameters()
                    .stream()
                    .collect(Collectors.toMap(MeasuredParameter::getParameterId
                            , parameter -> parameters.get(parameter.getId())));
        } else {
            return parameters;
        }
    }

    public void deleteRepair(RepairMeasurement repair) {
        measuredParameterService.deleteAll(repair.getMeasuredParameters());
        repository.deleteById(repair.getId());
    }

    private RepairMeasurement getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Repair with id=%s not found", id)));
    }

    private Set<RepairMeasurement> getAllByPredicate(Long equipmentId, Long elementId
                                                   , Long partElementId, Long repairLibraryId) {
        if (partElementId != null) {
            return repository.findAllByEquipmentIdAndElementIdAndPartElementIdAndRepairLibraryId(equipmentId
                                                                                               , elementId
                                                                                               , partElementId
                                                                                               , repairLibraryId);
        } else {
            return repository.findAllByEquipmentIdAndElementIdAndRepairLibraryId(equipmentId
                                                                               , elementId
                                                                               , repairLibraryId);
        }
    }


    private ParameterMeasurementBuilder getParameterMeasurementBuilder(RepairMeasurement repair) {
        return new ParameterMeasurementBuilder.Builder()
                .libraryDataType(MeasurementType.REPAIR)
                .repair(repair)
                .build();
    }
}