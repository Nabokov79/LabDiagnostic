package ru.nabokovsg.measurement.model.diagnostics;

public enum MeasurementStatus {

    ACCEPTABLE("Допустимое значение"),
    INVALID("Ниже предельного допустимого значения"),
    APPROACHING_INVALID("Приближается к минимальному допустимому значению"),
    REACHED_INVALID("Достигнуто предельное допустимое значение"),
    NO_STANDARD("Отсутствуют нормы допустимости измеренных значений"),
    NO_RESIDUAL_THICKNESS("Отсутствуют измерения остаточной толщины"),
    NO_IDENTIFIED_DEFECT("Отсутствуют дефекты для расчета остаточной толщины"),
    NO_MEASUREMENT("Отсутствует измерение");

    public final String label;

    MeasurementStatus(String label) {
        this.label = label;
    }
}