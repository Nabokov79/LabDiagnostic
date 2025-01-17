package ru.nabokovsg.measurement.сalculation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.mapper.diagnostics.CalculationMeasuredParameterMapper;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.diagnostics.CalculationMeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationMeasuredParameterServiceImpl implements CalculationMeasuredParameterService {

    private final CalculationMeasuredParameterMapper mapper;
    private final static String BLANK = " ";
    private final static String SEMICOLON = "; ";

    @Override
    public String getMeasuredParameters(Set<MeasuredParameter> measuredParameters
                                      , ParameterCalculationType calculation) {
        switch (calculation) {
            case MIN -> {
                return countMin(measuredParameters);
            }
            case MAX -> {
                return countMax(measuredParameters);
            }
            case MAX_MIN -> {
                return countMinMax(measuredParameters);
            }
            case NO_ACTION -> {
                return convertToString(measuredParameters);
            }
            default ->
                    throw new BadRequestException(
                            String.format("The type of defect calculation is not supported: %s", calculation));
        }
    }

    private String countMin(Set<MeasuredParameter> measuredParameters) {
        Map<String, CalculationMeasuredParameter> parameters = new HashMap<>();
        measuredParameters.forEach(parameter -> {
            CalculationMeasuredParameter calculationParameter = parameters.get(parameter.getParameterName());
            if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                setQuantityParameter(calculationParameter, parameter);
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
        return buildParameters(parameters);
    }

    private String countMax(Set<MeasuredParameter> measuredParameters) {
        log.info("Count max:");
        Map<String, CalculationMeasuredParameter> parameters = new HashMap<>();
        log.info("MeasuredParameter : {}", measuredParameters);
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
        return buildParameters(parameters);
    }

    private String countMinMax(Set<MeasuredParameter> measuredParameters) {
        Map<String, CalculationMeasuredParameter> parameters = new HashMap<>();
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
        return buildParameters(parameters);
    }

    private String convertToString(Set<MeasuredParameter> measuredParameters) {
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

    private void setQuantityParameter(CalculationMeasuredParameter calculationParameter, MeasuredParameter parameter) {
        mapper.mapToUpdateMinValue(calculationParameter, calculationParameter.getMinValue() + parameter.getValue());
    }

    private String buildParameters(Map<String, CalculationMeasuredParameter> calculateParameters) {
        String measuredParameters = BLANK;
        String quantity = BLANK;
        for (CalculationMeasuredParameter parameter : calculateParameters.values()) {
            if (parameter.getParameterName().equals(MeasurementParameterType.QUANTITY.label)) {
                quantity = buildParameter(parameter);
            } else {
                String measuredParameter = buildParameter(parameter);
                if (measuredParameters.isBlank()) {
                    measuredParameters = measuredParameter;
                } else {
                    measuredParameters = String.join(SEMICOLON, measuredParameters, measuredParameter);
                }
            }
        }
        return String.join(SEMICOLON, measuredParameters, quantity);
    }

    private String buildParameter(CalculationMeasuredParameter parameter) {
        String measuredParameter = parameter.getParameterName();
        log.info("measuredParameter Name : {}", measuredParameter);
        if (measuredParameter.equals(MeasurementParameterType.QUANTITY.label)) {
            measuredParameter = String.valueOf(parameter.getMinValue());
        } else {
            if (parameter.getMinValue() != null) {
                measuredParameter = String.join(BLANK,  "от", String.valueOf(parameter.getMinValue()));
            }
            if (parameter.getMaxValue() != null) {
                measuredParameter = String.join(BLANK,  "до", String.valueOf(parameter.getMaxValue()));
            }
        }
        return String.join(BLANK,  parameter.getParameterName(), measuredParameter, parameter.getUnitMeasurement());
    }
}
