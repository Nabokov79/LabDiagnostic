package ru.nabokovsg.referencebooks.validators;

import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;

import java.util.List;

public interface DefectValidator {

    void validate(DefectLibrary defect, List<MeasurementParameterLibrary> measuredParameters);
}