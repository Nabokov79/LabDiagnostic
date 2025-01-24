package ru.nabokovsg.measurement.service.common;

import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.diagnostics.CalculationMeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class ConvertingMeasuredParameterToStringServiceImpl implements ConvertingMeasuredParameterToStringService {

    private final static String BLANK = " ";
    private final static String SEMICOLON = "; ";

    @Override
    public String convertMeasuredParameter(Set<MeasuredParameter> measuredParameters) {
        Map<String, String> parameters = new HashMap<>();
        measuredParameters.forEach(parameter ->
                parameters.put(parameter.getParameterName(), String.join(BLANK, parameter.getParameterName()
                        , String.valueOf(parameter.getValue())
                        , parameter.getUnitMeasurement())));
        String measuredParameter = BLANK;
        String quantity = BLANK;
        for (String parameterName : parameters.keySet()) {
            if (parameterName.equals(MeasurementParameterType.QUANTITY.label)) {
                quantity = parameters.get(parameterName);
            } else {
                String parameter = parameters.get(parameterName);
                if (measuredParameter.isBlank()) {
                    measuredParameter = parameter;
                } else {
                    measuredParameter = String.join(SEMICOLON, measuredParameter, parameter);
                }
            }
        }
        return String.join(SEMICOLON, measuredParameter, quantity);
    }

    @Override
    public String convertCalculationParameters(Map<String, CalculationMeasuredParameter> parameters) {
        log.info("convert Calculation Parameters");
        String measuredParameters = BLANK;
        String quantity = BLANK;
        for (CalculationMeasuredParameter parameter : parameters.values()) {
            if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                 quantity = convertQuantityParameter(parameter);
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

    public String convertQuantityParameter(CalculationMeasuredParameter parameter) {
        log.info("convert Quantity Parameter");
        log.info("parameter: {}", parameter);
        int quantity = parameter.getMinValue().intValue();
        return String.join(BLANK, parameter.getParameterName()
                                , String.valueOf(quantity)
                                , parameter.getUnitMeasurement());
    }

    private String convertCalculationParameter(CalculationMeasuredParameter parameter) {
        log.info("convert Calculation Parameter");
        log.info("parameter: {}", parameter);
        String value = BLANK;
        if (parameter.getMinValue() != null) {
            log.info("Min Value");
            value = String.join(BLANK,  "от", String.valueOf(parameter.getMinValue()));
        }
        if (parameter.getMaxValue() != null) {
            log.info("Max Value");
            value = String.join(BLANK, "до", String.valueOf(parameter.getMaxValue()));
            log.info("value: {}", value);
        }
        return String.join(BLANK, parameter.getParameterName(), value, parameter.getUnitMeasurement());
    }
}