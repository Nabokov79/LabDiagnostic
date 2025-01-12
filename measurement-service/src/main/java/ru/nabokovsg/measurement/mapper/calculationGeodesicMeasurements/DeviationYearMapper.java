package ru.nabokovsg.measurement.mapper.calculationGeodesicMeasurements;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.DeviationYear;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ReferencePoint;

@Mapper(componentModel = "spring")
public interface DeviationYearMapper {

    @Mapping(source = "year", target = "year")
    @Mapping(source = "referencePoint.deviation", target = "deviation")
    @Mapping(source = "referencePoint", target = "referencePoint")
    @Mapping(target = "id", ignore = true)
    DeviationYear mapToDeviationYear(Integer year, ReferencePoint referencePoint);

    DeviationYear mapToUpdateDeviationYear(@MappingTarget DeviationYear deviationYear, Integer deviation);
}