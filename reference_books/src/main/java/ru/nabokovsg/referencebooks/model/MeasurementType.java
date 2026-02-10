package ru.nabokovsg.referencebooks.model;

import java.util.Optional;

public enum MeasurementType {

    DEFECT_METAL("ВИК основного металла;"),
    DEFECT_WELD("ВИК сварного соединения;"),
    INSPECTION("Визуальный контроль(осмотра);"),
    ULTRASONIC_INSPECTION("УЗК сварного соединения;"),
    RECOMMENDATION("Рекомендации по эксплуатации и ремонту;"),
    REPAIR("Ремонт элементов оборудования;"),
    GEODESIC("Геодезические измерения;"),
    HARDNESS("Измерение твердости металла;"),
    THICKNESS("Измерение толщины;");

    public final String label;

    MeasurementType(String label) {
        this.label = label;
    }

    public static Optional<MeasurementType> from(String measurementsType) {
        for (MeasurementType type : values()) {
            if (type.name().equalsIgnoreCase(measurementsType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}