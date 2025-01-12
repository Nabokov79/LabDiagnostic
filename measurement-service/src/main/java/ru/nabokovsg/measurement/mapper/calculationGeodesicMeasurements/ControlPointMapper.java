package ru.nabokovsg.measurement.mapper.calculationGeodesicMeasurements;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ControlPoint;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;

@Mapper(componentModel = "spring")
public interface ControlPointMapper {

    @Mapping(target = "id", ignore = true)
    ControlPoint mapToControlPoint(GeodesicMeasurements measurement
                                , Integer deviation
                                , CalculationGeodeticMeasuring calculationGeodeticPoints);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calculationGeodeticPoints", ignore = true)
    ControlPoint mapToUpdateControlPoint(@MappingTarget ControlPoint controlPoint
                                         , GeodesicMeasurements measurement
                                         , Integer deviation);
}