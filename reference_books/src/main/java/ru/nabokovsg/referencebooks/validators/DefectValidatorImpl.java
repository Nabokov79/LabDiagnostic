package ru.nabokovsg.referencebooks.validators;

import org.springframework.stereotype.Component;
import ru.nabokovsg.referencebooks.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.UpdateDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.NewMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.model.MeasurementParameterType;
import ru.nabokovsg.referencebooks.model.QualityAssessment;

import java.util.List;
import java.util.Objects;

@Component
public class DefectValidatorImpl implements DefectValidator {

    @Override
    public void validNew(NewDefectLibraryDto defect) {
        QualityAssessment qualityAssessmentType = QualityAssessment.from(defect.getQualityAssessment()).orElseThrow(() -> new BadRequestException(String.format("Оценка качества не поддерживается: %s", defect.getQualityAssessment())));
        notNulParameters(qualityAssessmentType, defect.getMeasuredParameters() == null);
        validateByWithoutNamingParameter(defect.isWithoutNamingParameter(), defect.getMeasuredParameters().size());
        List<String> names =  defect.getMeasuredParameters().stream().map(NewMeasurementParameterLibraryDto::getName).toList();
        validByQuantityMeasuredParameters(qualityAssessmentType, defect.isWithoutNamingParameter(), names);
        validAssessmentWeldedSite(qualityAssessmentType, defect.getAssessmentAreaMM(), defect.getAssessmentAreaPercentage(), defect.getDefectsQuantity(), defect.getTotalLengthMM(), defect.getTotalLengthPercentage());
        validateByParametersName(defect.getDefectsQuantity(), defect.getTotalLengthMM(), defect.getTotalLengthPercentage(), names);
        validNominalThickness(qualityAssessmentType, defect.getMinThickness(), defect.getMaxThickness());
    }

    private void notNulParameters(QualityAssessment qualityAssessmentType, boolean measuredParameters) {
        if (!qualityAssessmentType.equals(QualityAssessment.NOT_ACCEPTABLE) && !qualityAssessmentType.equals(QualityAssessment.NOT_PRODUCE) && measuredParameters) {
            throw new BadRequestException("Отсутствуют измеряемые параметры");
        }
    }

    private void validateByWithoutNamingParameter(boolean withoutNamingParameter, int size) {
        if (withoutNamingParameter && size != 1) {
            throw new BadRequestException("Недопустимое количество измеряемых параметров");
        }
    }

    private void validByQuantityMeasuredParameters(QualityAssessment qualityAssessmentType, boolean withoutNamingParameter
            , List<String> names) {
        boolean exception = withoutNamingParameter && names.size() != 1 && !qualityAssessmentType.equals(QualityAssessment.NOT_PRODUCE);
        if (!exception) {
            exception = qualityAssessmentType.equals(QualityAssessment.NOT_PRODUCE) && !names.isEmpty() && withoutNamingParameter && names.size() != 1;
        }
        if (exception) {
            throw new BadRequestException("Недопустимое количество измеряемых параметров");
        }
    }

    private void validAssessmentWeldedSite(QualityAssessment qualityAssessmentType, Double assessmentAreaMM
            , Double assessmentAreaPercentage, Integer defectsQuantity
            , Double totalLengthMM, Double totalLengthPercentage) {
        if (qualityAssessmentType.equals(QualityAssessment.RESIDUAL_THICKNESS) || qualityAssessmentType.equals(QualityAssessment.NOT_ACCEPTABLE) || qualityAssessmentType.equals(QualityAssessment.NOT_PRODUCE)) {
            throw new BadRequestException("Оценка участка сварного шва не доступна.");
        }
        boolean totalLength = totalLengthMM != null || totalLengthPercentage !=  null;
        if (defectsQuantity != null && totalLength) {
            throw new BadRequestException("Оценка участка сварного шва по двум параметрам не возможна.");
        }
        if (assessmentAreaMM != null && assessmentAreaPercentage != null) {
            throw new BadRequestException("Недопустимое количество оценочных участков.");
        }
        if (totalLengthMM != null && totalLengthPercentage !=  null) {
            throw new BadRequestException("Недопустимое количество значений суммарной длины.");
        }
    }

    private void validNominalThickness(QualityAssessment qualityAssessmentType, Float minThickness, Float maxThickness) {
        boolean assessment = qualityAssessmentType.equals(QualityAssessment.RESIDUAL_THICKNESS)
                              || qualityAssessmentType.equals(QualityAssessment.NOT_ACCEPTABLE)
                              || qualityAssessmentType.equals(QualityAssessment.NOT_PRODUCE);
        if (assessment && (minThickness != null || maxThickness != null)) {
            throw new BadRequestException("Указание номинальных толщин соединяемых элементов недоступно");
        }
        if (minThickness != null && maxThickness != null && Objects.equals(minThickness, maxThickness)) {
            throw new BadRequestException("Номинальные толщины соединяемых элементов не могут быть равны");
        }
    }

    private void validateByParametersName(Integer defectsQuantity, Double totalLengthMM, Double totalLengthPercentage, List<String> names) {
        String massage = "";
        if (defectsQuantity != null) {
            names.forEach(name -> {
                if (!name.equals("QUANTITY")) {
                    names.remove(name);
                }
            });
            massage = MeasurementParameterType.QUANTITY.label;
        }
        if (totalLengthMM != null && totalLengthPercentage != null) {
            names.forEach(name -> {
                if (!name.equals("LENGTH") && !name.equals("WIDTH") && !name.equals("HEIGHT")) {
                    names.remove(name);
                }
            });
            massage = String.join(", ", MeasurementParameterType.LENGTH.label, MeasurementParameterType.WIDTH.label, MeasurementParameterType.HEIGHT.label);
        }
        if (names.isEmpty()) {
            throw new BadRequestException(String.format("Отсутствуют один или несколько измеряемых параметров: %s", massage));
        }
    }


    @Override
    public void validUpdate(UpdateDefectLibraryDto defect) {

    }
}