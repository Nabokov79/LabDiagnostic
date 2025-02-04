package ru.nabokovsg.measurement.сalculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationMeasuredParameterMapper;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.diagnostics.CalculationMeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;
import ru.nabokovsg.measurement.service.common.ConvertingMeasuredParameterToStringService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CalculationMeasuredParameterServiceImpl implements CalculationMeasuredParameterService {

    private final CalculationMeasuredParameterMapper mapper;
    private final ConvertingMeasuredParameterToStringService stringBuilder;

    @Override
    public String calculateMeasuredParameters(Set<MeasuredParameter> parameters, ParameterCalculationType type) {
        Map<String, CalculationMeasuredParameter> calculationParameters = new HashMap<>();
        switch (type) {
            case MIN -> countMin(parameters, calculationParameters);
            case MAX -> countMax(parameters, calculationParameters);
            case MAX_MIN -> countMinMax(parameters, calculationParameters);
            default -> throw new BadRequestException(
                    String.format("The type of defect calculation is not supported: %s", type));
        }
        return stringBuilder.convertCalculationParameters(calculationParameters);
    }

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
                parameters.put(parameter.getParameterName(), calculateQuantity(calculationParameter, parameter));
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
                parameters.put(parameter.getParameterName(), calculateQuantity(calculationParameter, parameter));
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
                parameters.put(parameter.getParameterName(), calculateQuantity(calculationParameter, parameter));
            } else {
                if (calculationParameter == null) {
                    parameters.put(parameter.getParameterName(), mapper.mapToMinValue(parameter));
                } else {
                    double minValue = Math.min(calculationParameter.getMinValue(), parameter.getValue());
                    double maxValue;
                    if (calculationParameter.getMaxValue() != null) {
                        maxValue = Math.max(calculationParameter.getMaxValue(), parameter.getValue());
                    } else {
                        maxValue = Math.max(calculationParameter.getMinValue(), parameter.getValue());
                    }
                    mapper.mapToUpdateMinMaxValue(calculationParameter, minValue, maxValue);
                    parameters.put(parameter.getParameterName(), calculationParameter);
                }
            }
        });
    }

    private CalculationMeasuredParameter calculateQuantity(CalculationMeasuredParameter calculationParameter
                                                         , MeasuredParameter parameter) {
        if (calculationParameter == null) {
            return mapper.mapToMinValue(parameter);
        } else {
            mapper.mapToUpdateMinValue(calculationParameter, calculationParameter.getMinValue() + parameter.getValue());
            return calculationParameter;
        }
    }
}
