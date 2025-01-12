package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.dto.geodesicMeasurement.NewGeodesicMeasurementDto;
import ru.nabokovsg.measurement.dto.geodesicMeasurement.ResponseGeodesicMeasurementDto;
import ru.nabokovsg.measurement.dto.geodesicMeasurement.UpdateGeodesicMeasurementDto;

import java.util.List;

public interface GeodesicMeasurementsService {

    List<ResponseGeodesicMeasurementDto> save(NewGeodesicMeasurementDto measurementDto);

    List<ResponseGeodesicMeasurementDto> update(UpdateGeodesicMeasurementDto measurementDto);

    ResponseGeodesicMeasurementDto get(Long id);

    List<ResponseGeodesicMeasurementDto> getAll(Long equipmentId);
}