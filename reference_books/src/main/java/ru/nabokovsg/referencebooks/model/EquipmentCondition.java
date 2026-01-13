package ru.nabokovsg.referencebooks.model;

public enum EquipmentCondition {

    OLD("старое"),
    NEW("новое"),
    WITH_HEAT_CARRIER("с теплоносителем"),
    WITHOUT_HEAT_CARRIER("без теплоносителя");
    public final String label;

    EquipmentCondition(String label) {
        this.label = label;
    }
}