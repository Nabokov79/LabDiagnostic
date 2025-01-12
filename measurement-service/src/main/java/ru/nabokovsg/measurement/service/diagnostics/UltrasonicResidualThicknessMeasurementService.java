package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.dto.ultrasonicResidualThicknessMeasurement.ResponseUltrasonicResidualThicknessMeasurementDto;
import ru.nabokovsg.measurement.dto.ultrasonicResidualThicknessMeasurement.UltrasonicResidualThicknessMeasurementDto;

import java.util.List;

public interface UltrasonicResidualThicknessMeasurementService {

    ResponseUltrasonicResidualThicknessMeasurementDto save(UltrasonicResidualThicknessMeasurementDto measurementDto);

    ResponseUltrasonicResidualThicknessMeasurementDto get(Long id);

    List<ResponseUltrasonicResidualThicknessMeasurementDto> getAll(Long equipmentId, Long elementId, Long partElementId);

    void delete(Long id);
}