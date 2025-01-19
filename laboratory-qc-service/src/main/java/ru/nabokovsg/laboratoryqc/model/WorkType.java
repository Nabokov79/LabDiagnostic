package ru.nabokovsg.laboratoryqc.model;

import java.util.Optional;

public enum WorkType {

    TECHNICAL_SURVEY,
    PARTIAL_SURVEY,
    ULTRASONIC_THICKNESS,
    MEASUREMENT_HARDNESS,
    GEODETIC_MEASUREMENTS,
    VISUAL_CONTROL,
    ULTRASONIC_CONTROL;

    public static Optional<WorkType> from(String workType) {
        for (WorkType type : values()) {
            if (type.name().equalsIgnoreCase(workType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}