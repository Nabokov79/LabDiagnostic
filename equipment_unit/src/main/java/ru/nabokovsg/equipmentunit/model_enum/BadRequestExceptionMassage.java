package ru.nabokovsg.equipmentunit.model_enum;

public enum BadRequestExceptionMassage {

    NOT_STRUCTURE_ID("Не заданы идентификаторы структуры организации."),
    DUPLICATE("Обнаружен дубликат: ");

    public final String label;

    BadRequestExceptionMassage(String label) {
        this.label = label;
    }
}