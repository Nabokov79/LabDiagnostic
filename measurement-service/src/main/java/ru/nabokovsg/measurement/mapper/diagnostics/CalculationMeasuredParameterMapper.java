package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.diagnostics.CalculationMeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;

@Mapper(componentModel = "spring")
public interface CalculationMeasuredParameterMapper {

    @Mapping(source = "parameter.parameterName", target = "parameterName")
    @Mapping(source = "parameter.unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "parameter.value", target = "minValue")
    @Mapping(target = "maxValue", ignore = true)
    CalculationMeasuredParameter mapToMinValue(MeasuredParameter parameter);

    @Mapping(source = "parameter.parameterName", target = "parameterName")
    @Mapping(source = "parameter.unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "parameter.value", target = "maxValue")
    @Mapping(target = "minValue", ignore = true)
    CalculationMeasuredParameter mapToMaxValue(MeasuredParameter parameter);

    void mapToUpdateMinMaxValue(@MappingTarget CalculationMeasuredParameter calculationParameter
                                             , Double minValue
                                             , Double maxValue);

    void mapToUpdateMinValue(@MappingTarget CalculationMeasuredParameter parameter, Double minValue);

    void mapToUpdateMaxValue(@MappingTarget CalculationMeasuredParameter calculationParameter, Double maxValue);
}