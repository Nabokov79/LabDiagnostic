package ru.nabokovsg.referencebooks.model;

import java.util.Optional;

public enum QualityAssessment {

    NOT_ACCEPTABLE("Недопустимый"),
    NOT_PRODUCE("Оценка качества не выполняется"),
    RESIDUAL_THICKNESS("Оценка качества определяется по остаточной толщине элемента"),
    PARAMETERS("Оценка качества определяется по измеренным значениям параметров"),
    PARAMETER("Оценка качества определяется по измеренному значению одного из параметров");

    public final String label;

    QualityAssessment(String label) {
        this.label = label;
    }

    public static Optional<QualityAssessment> from(String qualityAssessment) {
        for (QualityAssessment type : values()) {
            if (type.name().equalsIgnoreCase(qualityAssessment)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}