package ru.nabokovsg.measurement.model.library;

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
}