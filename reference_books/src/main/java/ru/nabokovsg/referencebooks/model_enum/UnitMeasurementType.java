package ru.nabokovsg.referencebooks.model_enum;

import java.util.Optional;

public enum UnitMeasurementType {

    MM( "мм"),
    M_2("м2"),
    MM_2("мм2"),
    PIECES("шт"),
    NOT(" ");

    public final String label;

    UnitMeasurementType(String label) {
        this.label = label;
    }

    public static Optional<UnitMeasurementType> from(String unitMeasurement) {
        for (UnitMeasurementType type : values()) {
            if (type.name().equalsIgnoreCase(unitMeasurement)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}