package ru.nabokovsg.referencebooks.dto.measurementParameterLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления измеряемого параметра")
public class MeasurementParameterLibraryDto {

    @Schema(description = "Наименование")
    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be blank")
    private String name;
    @Schema(description = "Единица измерения")
    @NotNull(message = "unitMeasurement should not be null")
    @NotBlank(message = "unitMeasurement should not be blank")
    private String unitMeasurement;
    @Schema(description = "Требуемые вычисления параметра")
    @NotNull(message = "calculation should not be null")
    @NotBlank(message = "calculation should not be blank")
    private String calculation;
    @Schema(description = "Минимальное допустимое значение в мм")
    @Positive(message = "acceptableMinValueMM can only be positive")
    private Float acceptableMinValueMM;
    @Schema(description = "Минимальное допустимое значение в процентах")
    @Positive(message = "acceptableMinValuePercentage can only be positive")
    @Max(value = 100, message = "acceptableMinValuePercentage can't be more than 100")
    private Float acceptableMinValuePercentage;
    @Schema(description = "Максимальное допустимое значение")
    @Positive(message = "acceptableMaxValueMM can only be positive")
    private Float acceptableMaxValueMM;
    @Schema(description = "Минимальное допустимое значение в процентах")
    @Positive(message = "acceptableMinValuePercentage can only be positive")
    @Max(value = 100, message = "acceptableMaxValuePercentage can't be more than 100")
    private Float acceptableMaxValuePercentage;
    @Schema(description = "Параметр для расчета остаточной толщины")
    @NotNull(message = "calculateByResidualThickness should not be null")
    private Boolean calculateByResidualThickness;
}