package ru.nabokovsg.measurement.service.common;

import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.diagnostics.CalculationMeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;

import java.util.Map;
import java.util.Set;

@Service
public class ConvertingMeasuredParameterToStringServiceImpl implements ConvertingMeasuredParameterToStringService {

    private final static String BLANK = " ";
    private final static String SEMICOLON = "; ";

    @Override
    public String convertMeasuredParameter(Set<MeasuredParameter> measuredParameters) {
        String measuredParameter = BLANK;
        String quantity = BLANK;
        for (MeasuredParameter parameter : measuredParameters) {
            String parameterString = String.join(BLANK, parameter.getParameterName()
                                                      , String.valueOf(parameter.getValue())
                                                      , parameter.getUnitMeasurement());
            if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                quantity = convertQuantityParameter(parameter.getParameterName()
                                                  , parameter.getValue()
                                                  , parameter.getUnitMeasurement());
            } else {
                if (measuredParameter.isBlank()) {
                    measuredParameter = parameterString;
                } else {
                    measuredParameter = String.join(SEMICOLON, measuredParameter, parameterString);
                }
            }
        }
        return String.join(SEMICOLON, measuredParameter, quantity);
    }

    @Override
    public String convertCalculationParameters(Map<String, CalculationMeasuredParameter> parameters) {
        String measuredParameters = BLANK;
        String quantity = BLANK;
        for (CalculationMeasuredParameter parameter : parameters.values()) {
            if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                 quantity = convertQuantityParameter(parameter.getParameterName(), parameter.getMinValue(), parameter.getUnitMeasurement());
            } else {
                measuredParameters = buildMeasuredParameters(measuredParameters, parameter);
            }
        }
        return String.join(SEMICOLON, measuredParameters, quantity);
    }

    public String buildMeasuredParameters(String measuredParameters, CalculationMeasuredParameter parameter) {
        if (measuredParameters.isBlank()) {
            return convertCalculationParameter(parameter);
        } else {
            return String.join(SEMICOLON, measuredParameters, convertCalculationParameter(parameter));
        }
    }

    public String convertQuantityParameter(String parameterName, Double value, String unitMeasurement) {
        return String.join(BLANK, parameterName, String.valueOf(value.intValue()) , unitMeasurement);
    }

    private String convertCalculationParameter(CalculationMeasuredParameter parameter) {
        String value = BLANK;
        if (parameter.getMinValue() != null) {
            value = String.join(BLANK,  "от", String.valueOf(parameter.getMinValue()));
        }
        if (parameter.getMaxValue() != null) {
            value = String.join(BLANK, "до", String.valueOf(parameter.getMaxValue()));
        }
        return String.join(BLANK, parameter.getParameterName(), value, parameter.getUnitMeasurement());
    }
}