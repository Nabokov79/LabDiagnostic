package ru.nabokovsg.referencebooks.toStringService;

import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;

import java.util.List;

public interface ToStringService {

    String measuredParameters(List<MeasurementParameterLibrary> measuredParametersLibrary);

    String thickness(Float minThickness, Float maxThickness);

    String additionalEvaluationParameters(Double first, Double second);

    String getStandardSize(Double diameter, Double thickness);

    String getMeasurements(List<String> measurementsType);
}