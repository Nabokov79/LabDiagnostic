package ru.nabokovsg.referencebooks.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;
import ru.nabokovsg.referencebooks.model.QualityAssessment;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class DefectValidatorImpl implements DefectValidator {

    public void validateDefectLibrary(QualityAssessment qualityAssessmentType
            , DefectLibrary defect
            , List<MeasurementParameterLibrary> measuredParametersLibrary) {
        validMeasurementParameter(qualityAssessmentType, defect.getWithoutNamingParameter(), measuredParametersLibrary);
        validNominalWallThicknessElements(defect.getMinThickness(), defect.getMaxThickness());
        validAdditionalQualityAssessmentParameters(qualityAssessmentType
                                                 , defect.getAssessmentAreaMM(), defect.getAssessmentAreaPercentage()
                                                 , defect.getTotalLengthMM(), defect.getTotalLengthPercentage()
                                                 , defect.getDefectsQuantity());
        validCalculateByResidualThickness(qualityAssessmentType, measuredParametersLibrary);
    }

    private void validAdditionalQualityAssessmentParameters(QualityAssessment qualityAssessmentType
                                                          , Double assessmentAreaMM, Double assessmentAreaPercentage
                                                          , Double totalLengthMM, Double totalLengthPercentage
                                                          , Integer defectsQuantity) {
        switch (qualityAssessmentType) {
            case RESIDUAL_THICKNESS, NOT_PRODUCE, NOT_ACCEPTABLE -> {
                if (assessmentAreaMM != null || assessmentAreaPercentage != null
                        || totalLengthMM != null || totalLengthPercentage != null
                        || defectsQuantity != null) {
                    throw new BadRequestException("Недопустимая оценка качества.");
                }
            }
            default -> {
                if (assessmentAreaMM != null && assessmentAreaPercentage != null) {
                    throw new BadRequestException("Недопустимое количество оценочных участков.");
                }
                if (totalLengthMM != null && totalLengthPercentage != null) {
                    throw new BadRequestException("Недопустимое количество значений суммарной длины.");
                }
            }
        }
    }

    private void validNominalWallThicknessElements(Float minThickness, Float maxThickness) {
        if (minThickness != null && maxThickness != null && Objects.equals(minThickness, maxThickness)) {
            throw new BadRequestException("Номинальные толщины соединяемых элементов не могут быть равны");
        }
    }

    private void validMeasurementParameter(QualityAssessment qualityAssessmentType
                                         , boolean withoutNamingParameter
                                         , List<MeasurementParameterLibrary> measuredParametersLibrary) {
        if (withoutNamingParameter && measuredParametersLibrary.size() != 1) {
            throw new BadRequestException("Недопустимое количество измеряемых параметров.");
        }
        if (measuredParametersLibrary != null && qualityAssessmentType.equals(QualityAssessment.NOT_ACCEPTABLE)) {
            throw new BadRequestException("Дефект не подлежит измерению.");
        }
        if (measuredParametersLibrary != null) {
            switch (qualityAssessmentType) {
                case RESIDUAL_THICKNESS -> measuredParametersLibrary.forEach(parameter -> {
                    if (parameter.getAcceptableMinValue() != null || parameter.getAcceptableMaxValue() != null) {
                        throw new BadRequestException("Оценка качества выполняется по измерениям остаточной толщины.");
                    }
                });
                case NOT_PRODUCE -> measuredParametersLibrary.forEach(parameter -> {
                    if (parameter.getAcceptableMinValue() != null || parameter.getAcceptableMaxValue() != null) {
                        throw new BadRequestException("Дефект не подлежит оценке качества.");
                    }
                });
                default -> measuredParametersLibrary.forEach(parameter -> {
                    if (parameter.getAcceptableMinValue() == null || parameter.getAcceptableMaxValue() == null) {
                        throw new BadRequestException("Отсутствуют значения допустимых размеров измерения параметра");
                    }
                });
            }
        }
    }

    private void validCalculateByResidualThickness(QualityAssessment qualityAssessmentType
                                                 , List<MeasurementParameterLibrary> measuredParametersLibrary) {
        if (!qualityAssessmentType.equals(QualityAssessment.RESIDUAL_THICKNESS) && measuredParametersLibrary == null) {
            return;
        }
        Boolean[] calculateByResidualThickness = {false};
        measuredParametersLibrary.forEach(parameter -> {
            if (!calculateByResidualThickness[0]) {
                calculateByResidualThickness[0] = parameter.getCalculateByResidualThickness();
            }
        });
        if (qualityAssessmentType.equals(QualityAssessment.RESIDUAL_THICKNESS) && !calculateByResidualThickness[0]) {
            throw new BadRequestException("Отсутствуют параметры для расчета остаточной толщины.");
        }
    }
}