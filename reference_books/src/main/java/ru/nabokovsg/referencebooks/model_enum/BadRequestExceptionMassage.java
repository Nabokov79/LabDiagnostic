package ru.nabokovsg.referencebooks.model_enum;

public enum BadRequestExceptionMassage {

    DUPLICATE("Обнаружен дубликат: "),
    DOCUMENT_TYPE("Тип документа не поддерживается: "),
    DOCUMENT_STATUS("Статус документа не поддерживается:"),
    NOT_ELEMENTS("Отсутствуют элементы для копирования."),
    COPY_ELEMENTS("Все элементы скопированы."),
    PARAMETER_CALCULATION("Недопустимый тип расчета: "),
    MEASUREMENT_PARAMETER("Недопустимое наименование параметра: "),
    UNIT_MEASUREMENT("Недопустимая единица измерения: "),
    NOT_STANDARD_SIZE("Не заданы типоразмеры."),
    NOT_ACCEPTABLE_HARDNESS("Не заданы допустимые значения твердости."),
    NOT_ACCEPTABLE_THICKNESS("Отсутствуют допустимые значения."),
    NOT_MEASURED("Не подлежит измерению."),
    NOT_MEASURED_PARAMETERS("Отсутствуют измеряемые параметры."),
    UNACCEPTABLE_PARAMETERS("Недопустимое количество измеряемых параметров."),
    EVALUATION_UNACCEPTABLE("Оценка допустимости измеряемого параметра недоступна."),
    NOT_ACCEPTABLE_SIZE("Отсутствуют значения допустимых размеров измерения параметра для параметра: "),
    CANNOT_EQUAL("Допустимые значения параметра не могут быть равны."),
    INCORRECT_VALUE("Не верно заданы допустимые значения."),
    MISSING_PARAMETERS("Отсутствуют параметры для расчета остаточной толщины."),
    UNACCEPTABLE_QUANTITY("Недопустимое количество оценочных участков."),
    ESTIMATION_PLOT_UNACCEPTABLE("Оценка по участку недопустима."),
    ESTIMATION_LENGTH_UNACCEPTABLE("Оценка по суммарной длине недопустима."),
    ESTIMATION_QUANTITY_UNACCEPTABLE("Оценка по количеству дефектов недопустима."),
    DIAMETERS_CANNOT_EQUAL("Номинальные диаметры соединяемых элементов не могут быть равны."),
    DIAMETERS_INCORRECT("Не верно заданы номинальные диаметры соединяемых элементов."),
    THICKNESS_CANNOT_EQUAL("Номинальные толщины соединяемых элементов не могут быть равны."),
    THICKNESS_INCORRECT("Не верно заданы номинальные толщины соединяемых элементов."),
    QUALITY_ASSESSMENT("Оценка качества не поддерживается: ");

    public final String label;

    BadRequestExceptionMassage(String label) {
        this.label = label;
    }
}