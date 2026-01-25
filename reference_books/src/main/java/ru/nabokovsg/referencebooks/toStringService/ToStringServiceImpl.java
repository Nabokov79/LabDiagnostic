package ru.nabokovsg.referencebooks.toStringService;

import org.springframework.stereotype.Component;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;

import java.util.List;

@Component
public class ToStringServiceImpl implements ToStringService {

    @Override
    public String measuredParameters(List<MeasurementParameterLibrary> measuredParametersLibrary) {
        String[] measuredParameters = {null};
        if (measuredParametersLibrary == null) {
            return null;
        }
        measuredParametersLibrary.forEach(parameter -> {
            String measuredParameter = String.join(", ", parameter.getName()
                                             , String.join("",  parameter.getUnitMeasurement(), ";"));
            if (measuredParameters[0] != null) {
                measuredParameters[0] = String.join(" ", measuredParameters[0], measuredParameter);
            } else {
                measuredParameters[0] = measuredParameter;
            }
        });
        return measuredParameters[0];
    }

    @Override
    public String thickness(Float minThickness, Float maxThickness) {
        String standardSize = null;
        String from = "от";
        String before = "до";
        String inclusive = "(включительно)";
        String delimiter = " ";
        if (minThickness != null) {
            standardSize = String.join(delimiter, from, String.valueOf(minThickness));
        }
        if (maxThickness != null) {
            if (standardSize == null) {
                standardSize = String.join(delimiter, before, String.valueOf(maxThickness), inclusive);
            } else {
                standardSize = String.join(delimiter, standardSize, before, String.valueOf(maxThickness), inclusive);
            }
        }
        return standardSize;
    }

    @Override
    public String additionalEvaluationParameters(Double first, Double second) {
        if (first != null && second == null) {
            return String.join(" ", String.valueOf(first), "мм");
        }
        if (first == null && second != null) {
            return String.join(" ", String.valueOf(second), "%");
        }
        return null;
    }
}