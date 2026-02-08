package ru.nabokovsg.referencebooks.validators;

public interface SizeAcceptableCheckingValidator {

    void validateStandardSize(Double diameter, Double thickness);

    void validateAcceptableHardness(Integer minAcceptableHardness, Integer maxAcceptableHardness);

    void validateResidualThickness(Double  minAcceptableThicknessMM, Integer minAcceptableThicknessPercent
                                 , Double  maxAcceptableThinningMM, Integer maxAcceptableThinningPercent);
}