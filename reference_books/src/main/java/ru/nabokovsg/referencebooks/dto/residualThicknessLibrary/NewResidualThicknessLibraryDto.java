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
    @NotNull(message = "documentationLibraryId should not be null")
    @Positive(message = "documentationLibraryId can only be positive")
    private Long documentationLibraryId;
    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "equipmentLibraryId should not be null")
    @Positive(message = "equipmentLibraryId can only be positive")
    private Long equipmentLibraryId;
    @Schema(description = "Идентификатор элемента оборудования")
    @NotNull(message = "elementLibraryId should not be null")
    @Positive(message = "elementLibraryId can only be positive")
    private Long elementLibraryId;
    @Schema(description = "Идентификатор подэлемента элемента оборудования")
    @Positive(message = "partElementLibraryId can only be positive")
    private Long partElementLibraryId;
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