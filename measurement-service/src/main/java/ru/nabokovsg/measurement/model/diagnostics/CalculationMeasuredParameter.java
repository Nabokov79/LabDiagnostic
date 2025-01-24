package ru.nabokovsg.measurement.model.diagnostics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CalculationMeasuredParameter {

    private String parameterName;
    private Double minValue;
    private Double maxValue;
    private String unitMeasurement;

    @Override
    public String toString() {
        return "CalculationMeasuredParameter{" +
                "parameterName='" + parameterName + '\'' +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", unitMeasurement='" + unitMeasurement + '\'' +
                '}';
    }
}