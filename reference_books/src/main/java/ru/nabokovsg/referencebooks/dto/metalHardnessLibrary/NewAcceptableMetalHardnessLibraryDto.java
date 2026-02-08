package ru.nabokovsg.referencebooks.dto.metalHardnessLibrary;

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
@Schema(description = "Данные для добавления допустимой твердости металла")
public class NewAcceptableMetalHardnessLibraryDto {

    @Schema(description = "Идентификатор нормативно-технического документа")
    @NotNull(message = "documentationLibraryId should not be null")
    @Positive(message = "documentationLibraryId can only be positive")
    private Long documentationLibraryId;
    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "equipmentTypeId should not be null")
    @Positive(message = "equipmentTypeId can only be positive")
    private Long equipmentLibraryId;
    @Schema(description = "Идентификатор элемента оборудования")
    @NotNull(message = "elementId should not be null")
    @Positive(message = "elementId can only be positive")
    private Long elementLibraryId;
    @Schema(description = "Идентификатор подэлемента элемента оборудования")
    @Positive(message = "partElementLibraryId can only be positive")
    private Long partElementLibraryId;
    @Schema(description = "Минимальный допустимый диаметр")
    @Positive(message = "diameter can only be positive")
    private Double diameter;
    @Schema(description = "Минимальная допустимая толщина стенки")
    @Positive(message = "thickness can only be positive")
    private Double thickness;
    @Schema(description = "Минимальная допустимая твердость металла элемента")
    @NotNull(message = "minAcceptableHardness should not be null")
    @Positive(message = "minAcceptableHardness can only be positive")
    private Integer minAcceptableHardness;
    @Schema(description = "Максимальная допустимая твердость металла элемента")
    @Positive(message = "maxAcceptableHardness can only be positive")
    private Integer maxAcceptableHardness;
    @Schema(description = "Допустимая погрешность измерения")
    @NotNull(message = "measurementError should not be null")
    @Positive(message = "measurementError can only be positive")
    private Float measurementError;
}