package ru.nabokovsg.referencebooks.dto.metalHardnessLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения допустимой твердости металла")
public class UpdateMetalHardnessLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
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