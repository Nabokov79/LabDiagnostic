package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;

import java.util.Set;

public interface CalculationMeasuredParameterService {

    String getMeasuredParameters(Set<MeasuredParameter> measuredParameters, ParameterCalculationType calculation);
}