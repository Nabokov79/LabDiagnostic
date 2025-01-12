package ru.nabokovsg.measurement.mapper.calculationGeodesicMeasurements;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculatingOppositePoint;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;

@Mapper(componentModel = "spring")
public interface CalculatingOppositePointMapper {

    @Mapping(source = "firstPlaceNumber", target = "firstPlaceNumber")
    @Mapping(source = "secondPlaceNumber", target = "secondPlaceNumber")
    @Mapping(source = "deviation", target = "deviation")
    @Mapping(source = "acceptableDifference", target = "acceptableDifference")
    @Mapping(source = "calculationGeodeticPoints", target = "calculationGeodeticPoints")
    @Mapping(target = "id", ignore = true)
    CalculatingOppositePoint mapToCalculatingOppositePoint(
                                                          Integer firstPlaceNumber
                                                        , Integer secondPlaceNumber
                                                        , Integer deviation
                                                        , Boolean acceptableDifference
                                                        , CalculationGeodeticMeasuring calculationGeodeticPoints);


    void mapToUpdateCalculatingOppositePoint(
            @MappingTarget CalculatingOppositePoint calculatingOppositePoint
                                                , Integer deviation
                                                , Boolean acceptableDifference);
}