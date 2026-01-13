package ru.nabokovsg.referencebooks.dto.measurementParameterLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления измеряемого параметра")
public class NewMeasurementParameterLibraryDto {

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
    @Schema(description = "Минимальное допустимое значение")
    @Positive(message = "acceptableMinValue can only be positive")
    private Float acceptableMinValue;
    @Schema(description = "Максимальное допустимое значение")
    @Positive(message = "acceptableMaxValue can only be positive")
    private Float acceptableMaxValue;
    @Schema(description = "Параметр для расчета остаточной толщины")
    @NotNull(message = "calculateByResidualThickness should not be null")
    private Boolean calculateByResidualThickness;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewMeasurementParameterLibraryDto that = (NewMeasurementParameterLibraryDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}