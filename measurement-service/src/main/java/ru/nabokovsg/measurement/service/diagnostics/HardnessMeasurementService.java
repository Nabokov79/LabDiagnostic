package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.dto.hardnessMeasurement.NewHardnessMeasurementDto;
import ru.nabokovsg.measurement.dto.hardnessMeasurement.ResponseElementHardnessMeasurementDto;
import ru.nabokovsg.measurement.dto.hardnessMeasurement.UpdateHardnessMeasurementDto;

import java.util.List;

public interface HardnessMeasurementService {

    ResponseElementHardnessMeasurementDto save(NewHardnessMeasurementDto measurementDto);

    ResponseElementHardnessMeasurementDto update(UpdateHardnessMeasurementDto measurementDto);

    ResponseElementHardnessMeasurementDto get(Long id);

    List<ResponseElementHardnessMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId);

    void delete(Long id);
}