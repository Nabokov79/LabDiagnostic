package ru.nabokovsg.referencebooks.validators;

import org.springframework.stereotype.Component;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import java.util.Objects;

@Component
public class DefectValidatorImpl implements DefectValidator {

    @Override
    public void validate(DefectLibrary defect) {
        validNominalWallThicknessElements(defect.getMinThickness(), defect.getMaxThickness());
        validAdditionalQualityAssessmentParameters(defect.getQualityAssessment()
                                                 , defect.getAssessmentAreaMM(), defect.getAssessmentAreaPercentage()
                                                 , defect.getTotalLengthMM(), defect.getTotalLengthPercentage()
                                                 , defect.getDefectsQuantity());
    }

    private void validAdditionalQualityAssessmentParameters(String qualityAssessmentType
            , Double assessmentAreaMM, Double assessmentAreaPercentage
            , Double totalLengthMM, Double totalLengthPercentage
            , Integer defectsQuantity) {
        switch (qualityAssessmentType) {
            case "RESIDUAL_THICKNESS", "NOT_PRODUCE", "NOT_ACCEPTABLE" ->
                 unacceptableQualityAssessment(assessmentAreaMM, assessmentAreaPercentage
                                             , totalLengthMM, totalLengthPercentage, defectsQuantity);
            default -> {
                validAssessmentArea(assessmentAreaMM, assessmentAreaPercentage);
                validTotalLength(totalLengthMM, totalLengthPercentage);
            }
        }
    }

    private void validAssessmentArea(Double assessmentAreaMM, Double assessmentAreaPercentage) {
        if (assessmentAreaMM != null && assessmentAreaPercentage != null) {
            throw new BadRequestException("Недопустимое количество оценочных участков.");
        }
    }

    private void validTotalLength(Double totalLengthMM, Double totalLengthPercentage) {
        if (totalLengthMM != null && totalLengthPercentage != null) {
            throw new BadRequestException("Недопустимое количество значений суммарной длины.");
        }
    }

    private void unacceptableQualityAssessment(Double assessmentAreaMM, Double assessmentAreaPercentage
                          , Double totalLengthMM, Double totalLengthPercentage, Integer defectsQuantity) {
        Boolean[] additionalQualityAssessmentParameter = {assessmentAreaMM != null};
        if (!additionalQualityAssessmentParameter[0]) {
            additionalQualityAssessmentParameter[0] = assessmentAreaPercentage != null;
        }
        if (!additionalQualityAssessmentParameter[0]) {
            additionalQualityAssessmentParameter[0] = totalLengthMM != null;
        }
        if (!additionalQualityAssessmentParameter[0]) {
            additionalQualityAssessmentParameter[0] = totalLengthPercentage != null;
        }
        if (!additionalQualityAssessmentParameter[0]) {
            additionalQualityAssessmentParameter[0] = defectsQuantity != null;
        }
        if (additionalQualityAssessmentParameter[0]) {
            throw new BadRequestException("Недопустимая оценка качества.");
        }
    }

    private void validNominalWallThicknessElements(Float minThickness, Float maxThickness) {
        if (minThickness != null && maxThickness != null && Objects.equals(minThickness, maxThickness)) {
            throw new BadRequestException("Номинальные толщины соединяемых элементов не могут быть равны");
        }
    }
}