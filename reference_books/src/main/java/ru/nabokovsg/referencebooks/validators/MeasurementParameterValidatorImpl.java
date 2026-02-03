package ru.nabokovsg.referencebooks.validators;

import org.springframework.stereotype.Component;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class MeasurementParameterValidatorImpl implements MeasurementParameterValidator {

    @Override
    public void validateByWithoutNamingParameter(boolean withoutNamingParameter
                                               , List<MeasurementParameterLibrary> measuredParameters) {
        if (withoutNamingParameter) {
            throw new BadRequestException("Не подлежит измерению.");
        }
    }

    @Override
    public void validateDuplicateMeasuredParameters(List<MeasurementParameterLibrary> measuredParameters) {
        if (measuredParameters == null) {
            return;
        }
        Map<String, String> names = new HashMap<>(measuredParameters.size());
        measuredParameters.forEach(parameter -> {
            String name = names.get(parameter.getName());
            if (name == null) {
                names.put(parameter.getName(), parameter.getName());
            } else {
                throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label, name));
            }
        });
    }

    @Override
    public void validateByQuantityMeasuredParameters(boolean withoutNamingParameter
                                                   , List<MeasurementParameterLibrary> measuredParameters) {
        if (withoutNamingParameter && measuredParameters == null) {
            throw new BadRequestException("Отсутствуют измеряемые параметры.");
        }
        if (withoutNamingParameter && measuredParameters.size() != 1) {
            throw new BadRequestException("Недопустимое количество измеряемых параметров.");
        }
    }

    @Override
    public void validateNullAcceptableSizes(MeasurementParameterLibrary measuredParameter) {
        if (!getAcceptableSizesNull(measuredParameter)) {
            throw new BadRequestException("Оценка допустимости измеряемого параметра недоступна.");
        }
    }

    @Override
    public void validateNotNullAcceptableSizes(MeasurementParameterLibrary measuredParameter) {
        if (getAcceptableSizesNull(measuredParameter)) {
            throw new BadRequestException(
                    String.format("Отсутствуют значения допустимых размеров измерения параметра для параметра: %s"
                                                                                        , measuredParameter.getName()));
        }
        validateAcceptableValue(measuredParameter);
    }

    @Override
    public void validateAcceptableValue(MeasurementParameterLibrary measuredParameter) {
        if (equals(measuredParameter)) {
            throw new BadRequestException("Допустимые значения параметра не могут быть равны.");
        }
        if (more(measuredParameter)) {
            throw new BadRequestException("Не верно заданы допустимые значения.");
        }
    }

    private boolean equals(MeasurementParameterLibrary measuredParameter) {
        boolean equals = false;
        if (measuredParameter.getAcceptableMinValueMM() != null && measuredParameter.getAcceptableMaxValueMM() != null) {
            equals = measuredParameter.getAcceptableMinValueMM().equals(measuredParameter.getAcceptableMaxValueMM());
        }
        if (!equals && measuredParameter.getAcceptableMinValuePercentage() != null
                    && measuredParameter.getAcceptableMaxValuePercentage() != null) {
            equals = measuredParameter.getAcceptableMinValuePercentage()
                                      .equals(measuredParameter.getAcceptableMaxValuePercentage());
        }
        return equals;
    }

    private boolean more(MeasurementParameterLibrary measuredParameter) {
        boolean more = false;
        if (measuredParameter.getAcceptableMinValueMM() != null && measuredParameter.getAcceptableMaxValueMM() != null) {
            more = measuredParameter.getAcceptableMinValueMM() > measuredParameter.getAcceptableMaxValueMM();
        }
        if (!more && measuredParameter.getAcceptableMinValuePercentage() != null
                  && measuredParameter.getAcceptableMaxValuePercentage() != null) {
            more= measuredParameter.getAcceptableMinValuePercentage() >
                                                                     measuredParameter.getAcceptableMaxValuePercentage();
        }
        return more;
    }

    private boolean getAcceptableSizesNull(MeasurementParameterLibrary measuredParameter) {
        return Stream.of(measuredParameter.getAcceptableMinValueMM()
                        , measuredParameter.getAcceptableMinValuePercentage()
                        , measuredParameter.getAcceptableMaxValueMM()
                        , measuredParameter.getAcceptableMaxValuePercentage())
                .filter(Objects::nonNull)
                .toList()
                .isEmpty();
    }

    @Override
    public void validCalculateByResidualThickness(List<MeasurementParameterLibrary> measuredParametersLibrary) {
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

    @Override
    public void validateNullMeasurementParameters(List<MeasurementParameterLibrary> measuredParameters) {
        if (measuredParameters != null) {
            throw new BadRequestException("Не подлежит измерению.");
        }
    }
}