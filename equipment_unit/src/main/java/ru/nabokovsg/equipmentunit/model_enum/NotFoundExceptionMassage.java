package ru.nabokovsg.equipmentunit.model_enum;

public enum NotFoundExceptionMassage {

    SOURCE("Источник теплоснабжения не найден."),
    TECHNICAL_DEVICE("Техническое устройство не найдено."),
    EQUIPMENT_UNIT("Единица оборудования не найдена.");

    public final String label;

    NotFoundExceptionMassage(String label) {
        this.label = label;
    }
}