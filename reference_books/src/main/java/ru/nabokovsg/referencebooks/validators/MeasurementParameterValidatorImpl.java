package ru.nabokovsg.referencebooks.validators;

import org.springframework.stereotype.Component;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;

import java.util.List;

@Component
public class MeasurementParameterValidatorImpl implements MeasurementParameterValidator {

    @Override
    public List<MeasurementParameterLibrary> valid(String qualityAssessmentType
            , boolean withoutNamingParameter
            , List<MeasurementParameterLibrary> measuredParametersLibrary) {
        validateByWithoutNamingParameter(withoutNamingParameter, measuredParametersLibrary.size());
        validCalculateByResidualThickness(qualityAssessmentType,  measuredParametersLibrary);
        measuredParametersLibrary.forEach(parameter -> validateAcceptableValue(qualityAssessmentType
                                                                             , parameter.getAcceptableMinValue()
                                                                             , parameter.getAcceptableMaxValue()));
        return measuredParametersLibrary;
    }

    private void validateAcceptableValue(String qualityAssessmentType, Float acceptableMinValue, Float acceptableMaxValue) {
        switch (qualityAssessmentType) {
            case "RESIDUAL_THICKNESS" -> {
                if (acceptableMinValue != null || acceptableMaxValue != null) {
                    throw new BadRequestException("Оценка качества выполняется по измерениям остаточной толщины.");
                }
            }
            case "NOT_PRODUCE" -> {
                if (acceptableMinValue != null || acceptableMaxValue != null) {
                    throw new BadRequestException("Дефект не подлежит оценке качества.");
                }
            }
            default -> {
                if (acceptableMinValue == null || acceptableMaxValue == null) {
                    throw new BadRequestException("Отсутствуют значения допустимых размеров измерения параметра");
                }
            }
        }
    }

    private void validateByWithoutNamingParameter(boolean withoutNamingParameter, int size) {
        if (withoutNamingParameter && size != 1) {
            throw new BadRequestException("Недопустимое количество измеряемых параметров.");
        }
    }

    private void validCalculateByResidualThickness(String qualityAssessmentType
            , List<MeasurementParameterLibrary> measuredParametersLibrary) {
        if (qualityAssessmentType.equals("RESIDUAL_THICKNESS") && measuredParametersLibrary != null) {
            Boolean[] calculateByResidualThickness = {false};
            measuredParametersLibrary.forEach(parameter -> {
                if (!calculateByResidualThickness[0]) {
                    calculateByResidualThickness[0] = parameter.getCalculateByResidualThickness();
                }
            });
            if (!calculateByResidualThickness[0]) {
                throw new BadRequestException("Отсутствуют параметры для расчета остаточной толщины.");
            }
        }
    }
}