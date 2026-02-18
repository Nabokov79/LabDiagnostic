package ru.nabokovsg.referencebooks.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;
import ru.nabokovsg.referencebooks.model_enum.BadRequestExceptionMassage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectValidatorImpl implements DefectValidator {

    private final MeasurementParameterValidator parameterValidator;

    @Override
    public void validate(DefectLibrary defect, List<MeasurementParameterLibrary> measuredParameters) {
        parameterValidator.validateDuplicateMeasuredParameters(measuredParameters);
        switch (defect.getQualityAssessmentType()) {
            case RESIDUAL_THICKNESS -> {
                parameterValidator.validateByQuantityMeasuredParameters(defect.getWithoutNamingParameter(), measuredParameters);
                measuredParameters.forEach(parameterValidator::validateNullAcceptableSizes);
                parameterValidator.validCalculateByResidualThickness(measuredParameters);
                validateNotNullEvaluationAreaUnacceptable(defect);
                validateNotNullTotalLengthUnacceptable(defect);
                validateNotNullDefectsQuantity(defect);
                validStandardSize(defect);
            }
            case NOT_PRODUCE -> {
                parameterValidator.validateByQuantityMeasuredParameters(defect.getWithoutNamingParameter(), measuredParameters);
                measuredParameters.forEach(parameterValidator::validateNullAcceptableSizes);
                validateNotNullEvaluationAreaUnacceptable(defect);
                validateNotNullTotalLengthUnacceptable(defect);
                validateNotNullDefectsQuantity(defect);
                validStandardSize(defect);
            }
            case PARAMETER, PARAMETERS -> {
                parameterValidator.validateByQuantityMeasuredParameters(defect.getWithoutNamingParameter(), measuredParameters);
                measuredParameters.forEach(parameterValidator::validateNotNullAcceptableSizes);
                validateAssessmentArea(defect);
                validStandardSize(defect);
            }
            default -> {
                parameterValidator.validateByWithoutNamingParameter(defect.getWithoutNamingParameter(), measuredParameters);
                validateNotNullEvaluationAreaUnacceptable(defect);
                validateNotNullTotalLengthUnacceptable(defect);
                validateNotNullDefectsQuantity(defect);
                parameterValidator.validateNullMeasurementParameters(measuredParameters);
            }
        }
    }

    private void validateAssessmentArea(DefectLibrary defect) {
        if (defect.getAssessmentAreaMM() != null && defect.getAssessmentAreaPercentage() != null) {
            throw new BadRequestException(BadRequestExceptionMassage.UNACCEPTABLE_QUANTITY.label);
        }
    }

    private void validateNotNullEvaluationAreaUnacceptable(DefectLibrary defect) {
        if (defect.getAssessmentAreaMM() != null || defect.getAssessmentAreaPercentage() != null) {
            throw new BadRequestException(BadRequestExceptionMassage.ESTIMATION_PLOT_UNACCEPTABLE.label);
        }
    }

    private void validateNotNullTotalLengthUnacceptable(DefectLibrary defect) {
        if (defect.getTotalLengthMM() != null || defect.getTotalLengthPercentage() != null) {
            throw new BadRequestException(BadRequestExceptionMassage.ESTIMATION_LENGTH_UNACCEPTABLE.label);
        }
    }

    private void validateNotNullDefectsQuantity(DefectLibrary defect) {
        if (defect.getDefectsQuantity() != null) {
            throw new BadRequestException(BadRequestExceptionMassage.ESTIMATION_QUANTITY_UNACCEPTABLE.label);
        }
    }

    private void validStandardSize(DefectLibrary defect) {
        validNominalDiameters(defect);
        validNominalThicknesses(defect);
    }

    private void validNominalDiameters(DefectLibrary defect) {
        if (defect.getMinDiameter() != null && defect.getMaxDiameter() != null) {
            if (defect.getMinDiameter().equals(defect.getMaxDiameter())) {
                throw new BadRequestException(BadRequestExceptionMassage.DIAMETERS_CANNOT_EQUAL.label);
            }
            if (defect.getMaxDiameter() < defect.getMinDiameter()) {
                throw new BadRequestException(BadRequestExceptionMassage.DIAMETERS_INCORRECT.label);
            }
        }
    }

    private void validNominalThicknesses(DefectLibrary defect) {
        if (defect.getMinThickness() != null && defect.getMaxThickness() != null) {
            if (defect.getMinThickness().equals(defect.getMaxThickness())) {
                throw new BadRequestException(BadRequestExceptionMassage.THICKNESS_CANNOT_EQUAL.label);
            }
            if (defect.getMaxThickness() < defect.getMinThickness()) {
                throw new BadRequestException(BadRequestExceptionMassage.THICKNESS_INCORRECT.label);
            }
        }
    }
}