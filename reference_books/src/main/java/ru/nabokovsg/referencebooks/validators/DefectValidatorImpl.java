package ru.nabokovsg.referencebooks.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;

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
            //"NOT_ACCEPTABLE"
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
            throw new BadRequestException("Недопустимое количество оценочных участков.");
        }
    }

    private void validateNotNullEvaluationAreaUnacceptable(DefectLibrary defect) {
        if (defect.getAssessmentAreaMM() != null || defect.getAssessmentAreaPercentage() != null) {
            throw new BadRequestException("Оценка по участку недопустима.");
        }
    }

    private void validateNotNullTotalLengthUnacceptable(DefectLibrary defect) {
        if (defect.getTotalLengthMM() != null || defect.getTotalLengthPercentage() != null) {
            throw new BadRequestException("Оценка по суммарной длине недопустима.");
        }
    }

    private void validateNotNullDefectsQuantity(DefectLibrary defect) {
        if (defect.getDefectsQuantity() != null) {
            throw new BadRequestException("Оценка по количеству дефектов недопустима.");
        }
    }

    private void validStandardSize(DefectLibrary defect) {
        validNominalDiameters(defect);
        validNominalThicknesses(defect);
    }

    private void validNominalDiameters(DefectLibrary defect) {
        if (defect.getMinDiameter() != null && defect.getMaxDiameter() != null) {
            if (defect.getMinDiameter().equals(defect.getMaxDiameter())) {
                throw new BadRequestException("Номинальные диаметры соединяемых элементов не могут быть равны.");
            }
            if (defect.getMaxDiameter() < defect.getMinDiameter()) {
                throw new BadRequestException("Не верно заданы номинальные диаметры соединяемых элементов.");
            }
        }
    }

    private void validNominalThicknesses(DefectLibrary defect) {
        if (defect.getMinThickness() != null && defect.getMaxThickness() != null) {
            if (defect.getMinThickness().equals(defect.getMaxThickness())) {
                throw new BadRequestException("Номинальные толщины соединяемых элементов не могут быть равны.");
            }
            if (defect.getMaxThickness() < defect.getMinThickness()) {
                throw new BadRequestException("Не верно заданы номинальные толщины соединяемых элементов.");
            }
        }
    }
}