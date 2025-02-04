package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.dto.repairMeasurement.NewRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.ResponseRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.ResponseShortRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.UpdateRepairMeasurementDto;

import java.util.List;

public interface RepairMeasurementService {

    ResponseShortRepairMeasurementDto save(NewRepairMeasurementDto repairDto);

    ResponseShortRepairMeasurementDto update(UpdateRepairMeasurementDto repairDto);

    ResponseRepairMeasurementDto get(Long id);

    List<ResponseShortRepairMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId);

    void delete(Long id);
}