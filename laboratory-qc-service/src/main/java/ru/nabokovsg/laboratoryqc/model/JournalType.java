package ru.nabokovsg.laboratoryqc.model;

public enum JournalType {

    EQUIPMENT(" EQUIPMENT"),
    HEAT_SUPPLY("HEAT_SUPPLY"),
    QUALITY_CONTROL("QUALITY_CONTROL");

    public final String label;

    JournalType(String label) {
        this.label = label;
    }
}