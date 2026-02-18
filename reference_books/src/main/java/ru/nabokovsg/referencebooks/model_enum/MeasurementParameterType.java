package ru.nabokovsg.referencebooks.model_enum;

import java.util.Optional;

public enum MeasurementParameterType {

    LENGTH("длина"),
    WIDTH("ширина"),
    HEIGHT("высота"),
    DEPTH("глубина"),
    DIAMETER("диаметр"),
    AREA("площадь"),
    THICKNESS("толщина"),
    QUANTITY("количество");

    public final String label;

    MeasurementParameterType(String label) {
        this.label = label;
    }

    public static Optional<MeasurementParameterType> from(String name) {
        for (MeasurementParameterType type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}