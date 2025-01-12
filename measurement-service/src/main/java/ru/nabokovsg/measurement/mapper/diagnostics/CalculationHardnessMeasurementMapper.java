package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.diagnostics.HardnessMeasurement;

@Mapper(componentModel = "spring")
public interface CalculationHardnessMeasurementMapper {

    @Mapping(source = "measurementStatus", target = "measurementStatus")
    @Mapping(source = "status", target = "status")
    void mapWithMeasurementStatus(@MappingTarget HardnessMeasurement measurement
                                               , String measurementStatus
                                               , String status);
}