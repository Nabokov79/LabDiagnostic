package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.dto.repair.NewRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repair.ResponseRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repair.UpdateRepairMeasurementDto;

import java.util.List;

public interface RepairMeasurementService {

    ResponseRepairMeasurementDto save(NewRepairMeasurementDto repairDto);

    ResponseRepairMeasurementDto update(UpdateRepairMeasurementDto repairDto);

    ResponseRepairMeasurementDto get(Long id);

    List<ResponseRepairMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId);

    void delete(Long id);
}