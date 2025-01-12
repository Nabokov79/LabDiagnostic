package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.ultrasonicResidualThicknessMeasurement.ResponseUltrasonicResidualThicknessMeasurementDto;
import ru.nabokovsg.measurement.dto.ultrasonicResidualThicknessMeasurement.UltrasonicResidualThicknessMeasurementDto;
import ru.nabokovsg.measurement.model.diagnostics.UltrasonicResidualThicknessMeasurement;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface UltrasonicResidualThicknessMeasurementMapper {

    UltrasonicResidualThicknessMeasurement mapToUltrasonicResidualThicknessMeasurement(UltrasonicResidualThicknessMeasurementDto measurementDto
                                                             , LocalDate measurementDate);

    @Mapping(target = "equipmentId", ignore = true)
    @Mapping(target = "elementId", ignore = true)
    @Mapping(target = "partElementId", ignore = true)
    @Mapping(target = "measurementNumber", ignore = true)
    void mapToUpdateUltrasonicResidualThicknessMeasurement(
                                            @MappingTarget UltrasonicResidualThicknessMeasurement measurement
                                                         , UltrasonicResidualThicknessMeasurementDto measurementDto
                                                         , LocalDate measurementDate);

    ResponseUltrasonicResidualThicknessMeasurementDto mapToResponseUltrasonicThicknessMeasurementDto(
                                                                    UltrasonicResidualThicknessMeasurement measurement);
}