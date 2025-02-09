package ru.nabokovsg.measurement.service.common;

import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.diagnostics.CalculationMeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConvertingMeasuredParameterToStringServiceImpl implements ConvertingMeasuredParameterToStringService {

    private final static String BLANK = " ";
    private final static String SEMICOLON = ";";

    @Override
    public String convertMeasuredParameter(Set<MeasuredParameter> measuredParameters) {
        String measuredParameter = BLANK;
        String quantity = BLANK;
        String area = BLANK;
        measuredParameters = measuredParameters.stream()
                                               .sorted(Comparator.comparing(MeasuredParameter::getParameterName))
                                               .collect(Collectors.toCollection(LinkedHashSet::new));
        for (MeasuredParameter parameter : measuredParameters) {
            String parameterString = convertParameter(parameter.getParameterName()
                    , parameter.getValue()
                    , parameter.getUnitMeasurement());
            if (parameter.getParameterName().equals(MeasurementParameterType.AREA.label)) {
                area = parameterString;
            } else if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                quantity = convertQuantityParameter(parameter.getParameterName()
                        , parameter.getValue()
                        , parameter.getUnitMeasurement());
            } else {
                measuredParameter = buildMeasuredParameter(measuredParameter, parameterString);
            }
        }
        return buildString(measuredParameter, quantity, area);
    }

    @Override
    public String convertCalculationParameters(Map<String, CalculationMeasuredParameter> parameters) {
        String measuredParameter = BLANK;
        String quantity = convertCalculationParameter(parameters.get(MeasurementParameterType.QUANTITY.label));
        String area = convertCalculationParameter(parameters.get(MeasurementParameterType.AREA.label));
        for (CalculationMeasuredParameter parameter : sortedParameterString(parameters, quantity, area)) {
            measuredParameter = buildMeasuredParameter(measuredParameter, convertCalculationParameter(parameter));
        }
        return buildString(measuredParameter, quantity, area);
    }


    private String buildMeasuredParameter(String measuredParameter, String parameterString) {
        if (measuredParameter.isBlank()) {
            return parameterString;
        } else {
            return String.join(BLANK, measuredParameter, parameterString);
        }
    }

    private String buildString(String measuredParameter, String quantity, String area) {
        if (!area.isBlank()) {
            measuredParameter = String.join(BLANK, area, measuredParameter);
        }
        if (!quantity.isBlank()) {
            measuredParameter = String.join(BLANK, measuredParameter, quantity);
        }
        return measuredParameter.trim();
    }

    private LinkedHashSet<CalculationMeasuredParameter> sortedParameterString(
                                  Map<String, CalculationMeasuredParameter> parameters, String quantity, String area) {
        if (!quantity.isBlank()) {
            parameters.remove(MeasurementParameterType.QUANTITY.label);
        }
        if (!area.isBlank()) {
            parameters.remove(MeasurementParameterType.AREA.label);
        }
        return parameters.values().stream()
                                  .sorted(Comparator.comparing(CalculationMeasuredParameter::getParameterName))
                                  .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private String convertQuantityParameter(String parameterName, Double value, String unitMeasurement) {
        return String.join("", String.join(BLANK, parameterName
                                     , String.valueOf(value.intValue())
                                     , buildUnitMeasurement(unitMeasurement)));
    }

    private String convertParameter(String parameterName, Double value, String unitMeasurement) {
        return String.join("", String.join(BLANK, parameterName
                                                 , String.valueOf(value)
                                                 , buildUnitMeasurement(unitMeasurement)));
    }

    private String buildUnitMeasurement(String unitMeasurement) {
       return String.join("", unitMeasurement, SEMICOLON);
    }

    private String convertCalculationParameter(CalculationMeasuredParameter parameter) {
        if (parameter == null) {
            return BLANK;
        }
        if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
            return convertQuantityParameter(parameter.getParameterName()
                    , parameter.getMinValue()
                    , parameter.getUnitMeasurement());
        }
        String value = BLANK;
        if (parameter.getMinValue() != null) {
            value = String.join(BLANK,  "от", String.valueOf(parameter.getMinValue()));
        }
        if (parameter.getMaxValue() != null) {
            String parameterString = String.join(BLANK, "до", String.valueOf(parameter.getMaxValue()));
            if(value.isBlank()) {
                value = parameterString;
            } else {
                value = String.join(BLANK, value, parameterString);
            }
        }
        return String.join(BLANK, parameter.getParameterName(), value, buildUnitMeasurement(parameter.getUnitMeasurement()));
    }
}