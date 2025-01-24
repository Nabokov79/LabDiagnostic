package ru.nabokovsg.measurement.service.common;

import ru.nabokovsg.measurement.model.diagnostics.CalculationMeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;

import java.util.Map;
import java.util.Set;

public interface ConvertingMeasuredParameterToStringService {

    String convertMeasuredParameter(Set<MeasuredParameter> measuredParameters);

    String convertCalculationParameters(Map<String, CalculationMeasuredParameter> parameters);
}