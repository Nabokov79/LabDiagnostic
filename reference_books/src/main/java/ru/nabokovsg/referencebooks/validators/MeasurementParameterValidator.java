package ru.nabokovsg.referencebooks.validators;

import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;

import java.util.List;

public interface MeasurementParameterValidator {

    List<MeasurementParameterLibrary> valid(String qualityAssessmentType
            , boolean withoutNamingParameter
            , List<MeasurementParameterLibrary> measuredParametersLibrary);
}
