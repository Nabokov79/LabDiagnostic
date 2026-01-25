package ru.nabokovsg.referencebooks.validators;

import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;
import ru.nabokovsg.referencebooks.model.QualityAssessment;

import java.util.List;

public interface DefectValidator {

    void validateDefectLibrary(QualityAssessment qualityAssessmentType
            , DefectLibrary defect
            , List<MeasurementParameterLibrary> measuredParametersLibrary);
}