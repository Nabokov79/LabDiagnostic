package ru.nabokovsg.referencebooks.dto.measurementParameterLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Измеряемый параметр")
public class ResponseMeasurementParameterLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование")
    private String name;
    @Schema(description = "Единица измерения")
    private String unitMeasurement;
    @Schema(description = "Требуемые вычисления параметра")
    private String calculation;
    @Schema(description = "Минимальное допустимое значение в мм")
    private Float acceptableMinValueMM;
    @Schema(description = "Минимальное допустимое значение в процентах")
    private Float acceptableMinValuePercentage;
    @Schema(description = "Максимальное допустимое значение")
    private Float acceptableMaxValueMM;
    @Schema(description = "Минимальное допустимое значение в процентах")
    private Float acceptableMaxValuePercentage;
    @Schema(description = "Параметр для расчета остаточной толщины")
    private Boolean calculateByResidualThickness;
}