package ru.nabokovsg.referencebooks.dto.residualThicknessLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения допустимых толщин элементов оборудования")
public class UpdateResidualThicknessLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Типоразмер")
    @NotNull(message = "standardSize should not be null")
    @Positive(message = "standardSize can only be positive")
    private Double standardSize;
    @Schema(description = "Минимальная допустимая толщина стенки элемента")
    @Positive(message = "acceptableThickness can only be positive")
    private Double acceptableThickness;
    @Schema(description = "Минимальная допустимая толщина стенки элемента в процентах")
    @Positive(message = "acceptablePercent can only be positive")
    private Integer acceptablePercent;
    @Schema(description = "Допустимая погрешность измерения")
    @NotNull(message = "measurementError should not be null")
    @Positive(message = "measurementError can only be positive")
    private Float measurementError;
}