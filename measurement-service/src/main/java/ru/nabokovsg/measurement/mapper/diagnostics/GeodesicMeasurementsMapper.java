package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.geodesicMeasurement.NewGeodesicMeasurementDto;
import ru.nabokovsg.measurement.dto.geodesicMeasurement.ResponseGeodesicMeasurementDto;
import ru.nabokovsg.measurement.dto.geodesicMeasurement.UpdateGeodesicMeasurementDto;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;

@Mapper(componentModel = "spring")
public interface GeodesicMeasurementsMapper {

    GeodesicMeasurements mapToGeodesicMeasurementsPoint(NewGeodesicMeasurementDto measurementDto
                                                      , int sequentialNumber);

    GeodesicMeasurements mapToUpdateGeodesicMeasurementsPoint(@MappingTarget GeodesicMeasurements measurement
                                                                       , UpdateGeodesicMeasurementDto measurementDto);

    ResponseGeodesicMeasurementDto mapToResponseGeodesicMeasurementsPointDto(GeodesicMeasurements geodesicMeasurement);
}