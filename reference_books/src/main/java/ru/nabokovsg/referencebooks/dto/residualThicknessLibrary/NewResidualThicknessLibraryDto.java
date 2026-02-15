package ru.nabokovsg.referencebooks.dto.residualThicknessLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
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
@Schema(description = "Данные для добавления допустимых толщин элементов оборудования")
public class NewResidualThicknessLibraryDto {

    @Schema(description = "Идентификатор нормативно-технического документа")
    @NotNull(message = "documentation id should not be null")
    @Positive(message = "documentation id can only be positive")
    private Long documentationId;
    @Schema(description = "Идентификатор элемента оборудования")
    @NotNull(message = "element id should not be null")
    @Positive(message = "element id can only be positive")
    private Long elementId;
    @Schema(description = "Идентификатор подэлемента элемента оборудования")
    @Positive(message = "partElement id can only be positive")
    private Long partElementId;
    @Schema(description = "Диаметр элемента(подэлемента)")
    @Positive(message = "diameter can only be positive")
    private Double diameter;
    @Schema(description = "Толщина элемента(подэлемента)")
    @Positive(message = "thickness can only be positive")
    private Double thickness;
    @Schema(description = "Минимальная допустимая толщина стенки элемента в мм")
    @Positive(message = "minAcceptableThicknessMM can only be positive")
    private Double minAcceptableThicknessMM;
    @Schema(description = "Минимальная допустимая толщина стенки элемента в процентах")
    @Positive(message = "minAcceptableThicknessPercent can only be positive")
    @Max(value = 100, message = "minAcceptableThicknessPercent can't be more than 100")
    private Integer minAcceptableThicknessPercent;
    @Schema(description = "Максимальное допустимое утонение стенки элемента в мм")
    @Positive(message = "maxAcceptableThinningMM can only be positive")
    private Double maxAcceptableThinningMM;
    @Schema(description = "Максимальное допустимое утонение стенки элемента в процентах")
    @Positive(message = "maxAcceptableThinningPercent can only be positive")
    @Max(value = 100, message = "maxAcceptableThinningPercent can't be more than 100")
    private Integer maxAcceptableThinningPercent;
    @Schema(description = "Допустимая погрешность измерения")
    @NotNull(message = "measurementError should not be null")
    @Positive(message = "measurementError can only be positive")
    private Float measurementError;
}