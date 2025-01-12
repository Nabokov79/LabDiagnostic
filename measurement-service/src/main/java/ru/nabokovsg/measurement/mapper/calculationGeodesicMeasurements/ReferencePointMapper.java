package ru.nabokovsg.measurement.mapper.calculationGeodesicMeasurements;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ReferencePoint;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;

@Mapper(componentModel = "spring")
public interface ReferencePointMapper {

    @Mapping(target = "id", ignore = true)
    ReferencePoint mapToReferencePoint(GeodesicMeasurements measurement
                                     , CalculationGeodeticMeasuring calculationGeodeticPoints);

    @Mapping(source = "deviation", target = "deviation")
    @Mapping(source = "precipitation", target = "precipitation")
    @Mapping(source = "acceptablePrecipitation", target = "acceptablePrecipitation")
    @Mapping(target = "id", ignore = true)
    void addToReferencePoint(@MappingTarget ReferencePoint measurement
                                          , Integer deviation
                                          , Integer precipitation
                                          , Boolean acceptablePrecipitation);

    @Mapping(target = "id", ignore = true)
    ReferencePoint mapToUpdateReferencePoint(@MappingTarget ReferencePoint referencePoint
                                                          , GeodesicMeasurements measurement);
}