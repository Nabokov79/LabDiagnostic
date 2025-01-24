package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.client.MeasurementClient;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.dto.measuredParameter.NewMeasuredParameterDto;
import ru.nabokovsg.measurement.dto.measuredParameter.UpdateMeasuredParameterDto;
import ru.nabokovsg.measurement.dto.repair.NewRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repair.ResponseRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repair.UpdateRepairMeasurementDto;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.diagnostics.RepairMeasurementMapper;
import ru.nabokovsg.measurement.model.common.MeasurementType;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.ParameterMeasurementBuilder;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;
import ru.nabokovsg.measurement.model.library.RepairLibrary;
import ru.nabokovsg.measurement.repository.diagnostics.RepairMeasurementRepository;
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
    private final CalculationRepairMeasurementService calculationRepairMeasurementService;
    private final MeasurementClient client;

    @Override
    public ResponseRepairMeasurementDto save(NewRepairMeasurementDto repairDto) {
        Set<RepairMeasurement> repairs = getAllByPredicate(repairDto.getEquipmentId()
                , repairDto.getElementId()
                , repairDto.getPartElementId()
                , repairDto.getRepairLibraryId());
        Map<Long, Double> parameters = repairDto.getMeasuredParameters()
                                              .stream()
                                              .collect(Collectors.toMap(NewMeasuredParameterDto::getParameterLibraryId
                                                                      , NewMeasuredParameterDto::getValue));
        RepairMeasurement repair = getDuplicate(parameters, repairs);
        RepairLibrary repairLibrary = libraryService.getById(repairDto.getRepairLibraryId());
        if (repair == null) {
            EquipmentDto equipment = client.getEquipment(repairDto.getElementId(), repairDto.getPartElementId());
            repair = repository.save(mapper.mapToRepairMeasurement(repairDto, repairLibrary
                                                        , equipment.getElementName(), equipment.getPartElementName()));
            setMeasuredParameters(repair, repairLibrary, repairDto.getMeasuredParameters());
        }
        calculationRepairMeasurementService.save(repair, repairs, repairLibrary.getCalculation());
        return mapper.mapToResponseRepairMeasurementDto(repair);
    }

    @Override
    public ResponseRepairMeasurementDto update(UpdateRepairMeasurementDto repairDto) {
        RepairMeasurement repair = getById(repairDto.getId());
        Set<RepairMeasurement> repairs = getAllByPredicate(repair.getEquipmentId()
                                                                    , repair.getElementId()
                                                                    , repair.getPartElementId()
                                                                    , repair.getRepairLibraryId());
        Map<Long, Long> parametersDb = repair.getMeasuredParameters()
                            .stream()
                            .collect(Collectors.toMap(MeasuredParameter::getId, MeasuredParameter::getParameterId));
        Map<Long, Double> parameters = repairDto.getMeasuredParameters()
                                    .stream()
                                    .collect(Collectors.toMap(parameter -> parametersDb.get(parameter.getId())
                                                            , UpdateMeasuredParameterDto::getValue));
        RepairMeasurement duplicate = getDuplicate(parameters, repairs);
        RepairLibrary repairLibrary = libraryService.getById(repair.getRepairLibraryId());
        if (duplicate != null) {
            delete(repairDto.getId());
            repairs.remove(repair);
        }
        measuredParameterService.update(repair.getMeasuredParameters(), repairDto.getMeasuredParameters());
        calculationRepairMeasurementService.save(repair, repairs, repairLibrary.getCalculation());
        return mapper.mapToResponseRepairMeasurementDto(duplicate);
    }

    private void setMeasuredParameters(RepairMeasurement repair
            , RepairLibrary repairLibrary
            , List<NewMeasuredParameterDto> measuredParameters) {
        repair.setMeasuredParameters(measuredParameterService.save(
                new ParameterMeasurementBuilder.Builder()
                        .libraryDataType(MeasurementType.REPAIR)
                        .repair(repair)
                        .measurementParameterLibraries(repairLibrary.getMeasuredParameters())
                        .build()));
    }

    @Override
    public ResponseRepairMeasurementDto get(Long id) {
        return mapper.mapToResponseRepairMeasurementDto(getById(id));
    }

    @Override
    public List<ResponseRepairMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId) {
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
                                 .map(mapper::mapToResponseRepairMeasurementDto)
                                 .toList();
    }

    @Override
    public void delete(Long id) {
        RepairMeasurement repair = getById(id);
        repository.deleteById(id);
        measuredParameterService.deleteAll(repair.getMeasuredParameters());
    }

    private RepairMeasurement getDuplicate(Map<Long, Double> parameters, Set<RepairMeasurement> repairs) {
        for (RepairMeasurement repair : repairs) {
                if (measuredParameterService.compare(repair.getMeasuredParameters(), parameters)) {
                    return repair;
                }
            }
        return null;
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
}