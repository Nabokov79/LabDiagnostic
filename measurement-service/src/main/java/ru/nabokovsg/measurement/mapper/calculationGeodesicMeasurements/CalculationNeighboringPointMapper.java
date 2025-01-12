package ru.nabokovsg.measurement.mapper.calculationGeodesicMeasurements;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationNeighboringPoint;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;

@Mapper(componentModel = "spring")
public interface CalculationNeighboringPointMapper {

    @Mapping(target = "id", ignore = true)
    CalculationNeighboringPoint mapToCalculationNeighboringPoint(
                                                          Integer firstPlaceNumber
                                                        , Integer secondPlaceNumber
                                                        , Integer deviation
                                                        , Boolean acceptableDifference
                                                        , CalculationGeodeticMeasuring calculationGeodeticPoints);

    void mapToUpdateNeighboringPoint(@MappingTarget CalculationNeighboringPoint neighboringPoint
                                                  , Integer deviation
                                                  , Boolean acceptableDifference);
}