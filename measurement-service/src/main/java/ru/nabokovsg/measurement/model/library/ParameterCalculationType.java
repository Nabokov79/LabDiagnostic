package ru.nabokovsg.measurement.model.library;

import java.util.Optional;

public enum ParameterCalculationType {

    MAX,
    MIN,
    MAX_MIN,
    NO_ACTION;

    public static Optional<ParameterCalculationType> from(String calculation) {
        for (ParameterCalculationType type : values()) {
            if (type.name().equalsIgnoreCase(calculation)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}