package ru.nabokovsg.referencebooks.model_enum;

public enum NotFoundExceptionMassage {

    ORGANIZATION("Организация не обнаружена."),
    BRANCH("Филиал не обнаружен."),
    DEPARTMENT("Подразделение не обнаружено."),
    HEAT_SUPPLY_SITE("Участок тепловой сети не обнаружен."),
    TECHNICAL_DEVICE("Техническое устройство не обнаружено."),
    EMPLOYEE("Сотрудник не найден."),
    GEODESIC("Допустимые отклонения значений геодезических измерений не обнаружены."),
    DOCUMENT("Документ не найден."),
    DIAGNOSIS("Диагностика не обнаружена."),
    EQUIPMENT("Оборудование не обнаружено."),
    ELEMENT("Элемент не обнаружен."),
    PART_ELEMENT("Подэлемент не обнаружен."),
    RECOMMENDATION("Рекомендация не найдена."),
    METAL_HARDNESS("Допустимое значение твердости металла не обнаружено."),
    RESIDUAL_THICKNESS("Допустимые значения остаточной толщины не обнаружены."),
    REPAIR("Ремонт не обнаружен."),
    HEAT_SUPPLY_SOURCE("Источник теплоснабжения не обнаружен."),
    DEFECT("Дефект не обнаружен.");


    public final String label;

    NotFoundExceptionMassage(String label) {
        this.label = label;
    }
}