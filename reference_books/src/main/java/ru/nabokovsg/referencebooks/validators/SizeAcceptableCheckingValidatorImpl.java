package ru.nabokovsg.referencebooks.validators;

import org.springframework.stereotype.Component;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.model_enum.BadRequestExceptionMassage;

@Component
public class SizeAcceptableCheckingValidatorImpl implements SizeAcceptableCheckingValidator {

    @Override
    public void validateStandardSize(Double diameter, Double thickness) {
        if (diameter == null && thickness == null) {
            throw new BadRequestException(BadRequestExceptionMassage.NOT_STANDARD_SIZE.label);
        }
    }

    @Override
    public void validateAcceptableHardness(Integer minAcceptableHardness, Integer maxAcceptableHardness) {
        if (minAcceptableHardness == null && maxAcceptableHardness == null) {
            throw new BadRequestException(BadRequestExceptionMassage.NOT_ACCEPTABLE_HARDNESS.label);
        }
    }

    @Override
    public void validateResidualThickness(Double minAcceptableThicknessMM, Integer minAcceptableThicknessPercent
                                        , Double maxAcceptableThinningMM, Integer maxAcceptableThinningPercent) {
        if (minAcceptableThicknessMM == null && minAcceptableThicknessPercent == null
                                        && maxAcceptableThinningMM == null && maxAcceptableThinningPercent == null) {
            throw new BadRequestException(BadRequestExceptionMassage.NOT_ACCEPTABLE_THICKNESS.label);
        }
    }
}