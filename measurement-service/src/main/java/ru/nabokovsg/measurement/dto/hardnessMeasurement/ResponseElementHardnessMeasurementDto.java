package ru.nabokovsg.measurement.dto.hardnessMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Рассчитанные результаты измерений твердости металла элемента, подэлемента оборудования")
public class ResponseElementHardnessMeasurementDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Наименование элемента")
    private String elementName;
    @Schema(description = "Наименование элемента")
    private String partElementName;
    @Schema(description = "Типоразмер элемента, подэлемента")
    private String standardSizeString;
    @Schema(description = "Номер измерения(по схеме)")
    private Integer measurementNumber;
    @Schema(description = "Измеренное значение")
    private Integer measurementValue;
    @Schema(description = "Допустимость измеренного значения")
    private String validity;
}