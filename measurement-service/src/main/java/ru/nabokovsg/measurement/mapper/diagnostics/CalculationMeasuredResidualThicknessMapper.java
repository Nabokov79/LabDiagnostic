package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.model.diagnostics.UltrasonicResidualThicknessMeasurement;

@Mapper(componentModel = "spring")
public interface CalculationMeasuredResidualThicknessMapper {

    void mapWithDataCalculation(@MappingTarget UltrasonicResidualThicknessMeasurement measurement
                                             , EquipmentDto equipment
                                             , Double maxCorrosion
                                             , Double residualThickness
                                             , Double minAcceptableValue);

    void mapWithMeasurementStatus(@MappingTarget UltrasonicResidualThicknessMeasurement measurement
                                               , String measurementStatus, String status);
}