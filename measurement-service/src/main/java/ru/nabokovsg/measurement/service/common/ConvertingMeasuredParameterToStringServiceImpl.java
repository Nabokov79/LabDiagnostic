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
    private final static String SEMICOLON = ";";

    @Override
    public String convertMeasuredParameter(Set<MeasuredParameter> measuredParameters) {
        String measuredParameter = BLANK;
        String quantity = BLANK;
        for (MeasuredParameter parameter : measuredParameters) {
            if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                quantity = convertQuantityParameter(parameter.getParameterName()
                                                  , parameter.getValue()
                                                  , parameter.getUnitMeasurement());
            } else {
                String parameterString = convertParameter(parameter.getParameterName()
                                                        , parameter.getValue()
                                                        , parameter.getUnitMeasurement());
                if (measuredParameter.isBlank()) {
                    measuredParameter = String.join("", parameterString, SEMICOLON);
                } else {
                    measuredParameter = String.join("", String.join(BLANK, measuredParameter, parameterString)
                                                              , SEMICOLON);
                }
            }
        }
        if (quantity.isBlank()) {
            return measuredParameter;
        }
        return String.join(BLANK, measuredParameter, quantity);
    }

    @Override
    public String convertCalculationParameters(Map<String, CalculationMeasuredParameter> parameters) {
        String measuredParameters = BLANK;
        String quantity = BLANK;
        for (CalculationMeasuredParameter parameter : parameters.values()) {
            if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                 quantity = convertQuantityParameter(parameter.getParameterName()
                                                   , parameter.getMinValue()
                                                   , parameter.getUnitMeasurement());
            } else {
                if (measuredParameters.isBlank()) {
                    measuredParameters = convertCalculationParameter(parameter);
                } else {
                    measuredParameters = String.join(BLANK, measuredParameters, convertCalculationParameter(parameter));
                }
            }
        }
        if (!quantity.isBlank()) {
            measuredParameters = String.join(BLANK, measuredParameters, quantity);
        }
        return measuredParameters;
    }

    private String convertQuantityParameter(String parameterName, Double value, String unitMeasurement) {
        String quantity =  String.join("", String.join(BLANK, parameterName
                                                                    , String.valueOf(value.intValue())
                                                                    , unitMeasurement));
        return String.join("", quantity, SEMICOLON);
    }

    private String convertParameter(String parameterName, Double value, String unitMeasurement) {
        String parameterString =  String.join("", String.join(BLANK, parameterName
                                                        , String.valueOf(value)
                                                        , unitMeasurement));
        return String.join("", parameterString, SEMICOLON);
    }

    private String convertCalculationParameter(CalculationMeasuredParameter parameter) {
        String value = BLANK;
        String unitMeasurement = String.join("", parameter.getUnitMeasurement(), SEMICOLON);
        if (parameter.getMinValue() != null) {
            value = String.join(BLANK,  "от", String.valueOf(parameter.getMinValue()));
        }
        if (parameter.getMaxValue() != null) {
            if(value.isBlank()) {
                value = String.join(BLANK, "до", String.valueOf(parameter.getMaxValue()));
            } else {
                value = String.join(BLANK, value, "до", String.valueOf(parameter.getMaxValue()));
            }
        }
        return String.join(BLANK, parameter.getParameterName(), value, unitMeasurement);
    }
}