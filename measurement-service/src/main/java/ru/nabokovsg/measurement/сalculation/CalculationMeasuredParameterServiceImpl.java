package ru.nabokovsg.measurement.сalculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationMeasuredParameterMapper;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.diagnostics.CalculationMeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CalculationMeasuredParameterServiceImpl implements CalculationMeasuredParameterService {

    private final CalculationMeasuredParameterMapper mapper;

    @Override
    public String getMeasuredParameters(Set<MeasuredParameter> parameters, ParameterCalculationType type) {
        switch (type) {
            case MIN, MAX, MAX_MIN, NO_ACTION -> {
                return null;
            }
            default ->
                    throw new BadRequestException(
                            String.format("The type of defect calculation is not supported: %s", type));
        }
    }

    @Override
    public void countMin(Set<MeasuredParameter> measuredParameters, Map<String, CalculationMeasuredParameter> parameters) {
        measuredParameters.forEach(parameter -> {
            CalculationMeasuredParameter calculationParameter = parameters.get(parameter.getParameterName());
            if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                if (calculationParameter == null) {
                    calculationParameter = mapper.mapToMinValue(parameter);
                } else {
                    setQuantityParameter(calculationParameter, parameter);
                }
                parameters.put(parameter.getParameterName(), calculationParameter);
            } else {
                if (calculationParameter == null) {
                    parameters.put(parameter.getParameterName(), mapper.mapToMinValue(parameter));
                } else {
                    mapper.mapToUpdateMinValue(calculationParameter, Math.min(calculationParameter.getMinValue()
                            , parameter.getValue()));
                    parameters.put(parameter.getParameterName(), calculationParameter);
                }
            }
        });
    }

    @Override
    public void countMax(Set<MeasuredParameter> measuredParameters, Map<String, CalculationMeasuredParameter> parameters) {
        measuredParameters.forEach(parameter -> {
            CalculationMeasuredParameter calculationParameter = parameters.get(parameter.getParameterName());
            if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                if (calculationParameter == null) {
                    calculationParameter = mapper.mapToMinValue(parameter);
                } else {
                    setQuantityParameter(calculationParameter, parameter);
                }
                parameters.put(parameter.getParameterName(), calculationParameter);
            } else {
                if (calculationParameter == null) {
                    parameters.put(parameter.getParameterName(), mapper.mapToMaxValue(parameter));
                } else {
                    mapper.mapToUpdateMaxValue(calculationParameter, Math.max(calculationParameter.getMaxValue()
                                                                            , parameter.getValue()));
                    parameters.put(parameter.getParameterName(), calculationParameter);
                }
            }
        });
    }

    @Override
    public void countMinMax(Set<MeasuredParameter> measuredParameters, Map<String, CalculationMeasuredParameter> parameters) {
        measuredParameters.forEach(parameter -> {
            CalculationMeasuredParameter calculationParameter = parameters.get(parameter.getParameterName());
            if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                setQuantityParameter(calculationParameter, parameter);
            } else {
                if (calculationParameter == null) {
                    calculationParameter = mapper.mapToMinValue(parameter);
                    mapper.mapToUpdateMaxValue(calculationParameter, parameter.getValue());
                    parameters.put(parameter.getParameterName(), calculationParameter);
                } else {
                    double minValue = Math.min(calculationParameter.getMinValue(), parameter.getValue());
                    double maxValue = Math.max(calculationParameter.getMaxValue(), parameter.getValue());
                    if (minValue == maxValue) {
                        mapper.mapToUpdateMinValue(calculationParameter, minValue);
                    } else {
                        mapper.mapToUpdateMinMaxValue(calculationParameter, minValue , maxValue);
                    }
                    parameters.put(parameter.getParameterName(), calculationParameter);
                }
            }
        });
    }

    private void setQuantityParameter(CalculationMeasuredParameter calculationParameter, MeasuredParameter parameter) {
        mapper.mapToUpdateMinValue(calculationParameter, calculationParameter.getMinValue() + parameter.getValue());
    }
}
