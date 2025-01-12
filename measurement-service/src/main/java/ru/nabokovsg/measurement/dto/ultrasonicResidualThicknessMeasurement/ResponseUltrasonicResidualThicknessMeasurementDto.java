package ru.nabokovsg.measurement.dto.ultrasonicResidualThicknessMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Результаты ультразвукового измерения толщины")
public class ResponseUltrasonicResidualThicknessMeasurementDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование элемента")
    private String elementName;
    @Schema(description = "Наименование подэлемента")
    private String partElementName;
    @Schema(description = "Типоразмер элемента, подэлемента")
    private String standardSize;
    @Schema(description = "Номер измерения(по схеме)")
    private Integer measurementNumber;
    @Schema(description = "Минимальное измеренное значение")
    private Double minMeasurementValue;
    @Schema(description = "Максимальное измеренное значение")
    private Double maxMeasurementValue;
    @Schema(description = "Минимальное допустимое значение")
    private Double maxCorrosion;
    @Schema(description = "Остаточная толщина")
    private Double residualThickness;
    @Schema(description = "Минимальное допустимое значение")
    private Double minAcceptableValue;
    @Schema(description = "Статус допустимости значений остаточной толщины")
    private String measurementStatus;
}