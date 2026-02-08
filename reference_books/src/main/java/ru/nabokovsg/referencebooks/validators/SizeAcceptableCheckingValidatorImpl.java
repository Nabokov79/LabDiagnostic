package ru.nabokovsg.referencebooks.validators;

import org.springframework.stereotype.Component;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;

@Component
public class SizeAcceptableCheckingValidatorImpl implements SizeAcceptableCheckingValidator {

    @Override
    public void validateStandardSize(Double diameter, Double thickness) {
        if (diameter == null && thickness == null) {
            throw new BadRequestException("Не заданы типоразмеры.");
        }
    }

    @Override
    public void validateAcceptableHardness(Integer minAcceptableHardness, Integer maxAcceptableHardness) {
        if (minAcceptableHardness == null && maxAcceptableHardness == null) {
            throw new BadRequestException("Не заданы допустимые значения твердости");
        }
    }

    @Override
    public void validateResidualThickness(Double minAcceptableThicknessMM, Integer minAcceptableThicknessPercent
                                        , Double maxAcceptableThinningMM, Integer maxAcceptableThinningPercent) {
        if (minAcceptableThicknessMM == null && minAcceptableThicknessPercent == null
                                        && maxAcceptableThinningMM == null && maxAcceptableThinningPercent == null) {
            throw new BadRequestException("Отсутствуют допустимые значения.");
        }
    }
}