package ru.nabokovsg.referencebooks.validators;

import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;

import java.util.List;

public interface MeasurementParameterValidator {

    void validateByWithoutNamingParameter(boolean withoutNamingParameter
                                        , List<MeasurementParameterLibrary> measuredParameters);

    void validateDuplicateMeasuredParameters(List<MeasurementParameterLibrary> measuredParameters);

    void validateByQuantityMeasuredParameters(boolean withoutNamingParameter
                                           , List<MeasurementParameterLibrary> measuredParameters);

    void validateNullAcceptableSizes(MeasurementParameterLibrary measuredParameter);

    void validateNotNullAcceptableSizes(MeasurementParameterLibrary measuredParameter);

    void validCalculateByResidualThickness(List<MeasurementParameterLibrary> measuredParametersLibrary);

    void validateNullMeasurementParameters(List<MeasurementParameterLibrary> measuredParameters);

    void validateAcceptableValue(MeasurementParameterLibrary measuredParameter);
}
