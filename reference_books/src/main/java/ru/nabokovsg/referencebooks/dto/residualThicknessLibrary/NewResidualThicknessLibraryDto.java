package ru.nabokovsg.referencebooks.dto.residualThicknessLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
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