package ru.nabokovsg.referencebooks.model_enum;

import java.util.Optional;

public enum ParameterCalculationType {

    MAX("Расчет максимального значения"),
    MIN("Расчет минимального значения"),
    MAX_MIN("Расчет минимального и максимального значения"),
    NO_ACTION("Расчет не выполняется");

    public final String label;

    ParameterCalculationType(String label) {
        this.label = label;
    }

    public static Optional<ParameterCalculationType> from(String calculation) {
        for (ParameterCalculationType type : values()) {
            if (type.name().equalsIgnoreCase(calculation)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}