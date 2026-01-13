package ru.nabokovsg.referencebooks.model;

public enum ExceptionMassage {

    DUPLICATE("Обнаружен дубликат: "),
    NOT_PARAMETERS("Отсутствуют параметры для измерения."),
    INCORRECT_DEFECT("Некорректная запись дефекта."),
    NOT_PARAMETER("Отсутствует параметр для расчета остаточной толщины."),
    PARAMETERS("Указано белее одного параметра."),
    NOT_DEFECT("Дефект не обнаружен.");

    public final String label;

    ExceptionMassage(String label) {
        this.label = label;
    }
}