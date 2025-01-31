package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.CalculationMeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;

import java.util.Map;
import java.util.Set;

public interface CalculationMeasuredParameterService {

    String calculateMeasuredParameters(Set<MeasuredParameter> parameters, ParameterCalculationType type);
    String getMeasuredParameters(Set<MeasuredParameter> parameters, ParameterCalculationType type);

    void countMin(Set<MeasuredParameter> measuredParameters, Map<String, CalculationMeasuredParameter> parameters);

    void countMax(Set<MeasuredParameter> measuredParameters, Map<String, CalculationMeasuredParameter> parameters);

    void countMinMax(Set<MeasuredParameter> measuredParameters, Map<String, CalculationMeasuredParameter> parameters);
}