package ru.nabokovsg.measurement.dto.acceptableMetalHardness;

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
public class NewAcceptableMetalHardnessDto {

    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "equipmentTypeId should not be null")
    @Positive(message = "equipmentTypeId can only be positive")
    private Long equipmentLibraryId;
    @Schema(description = "Идентификатор элемента оборудования")
    @NotNull(message = "elementId should not be null")
    @Positive(message = "elementId can only be positive")
    private Long elementLibraryId;
    @Schema(description = "Идентификатор подэлемента элемента оборудования")
    private Long partElementLibraryId;
    @Schema(description = "Минимальный допустимый диаметр")
    @NotNull(message = "minAcceptableDiameter should not be null")
    @Positive(message = "minAcceptableDiameter can only be positive")
    private Integer minAcceptableDiameter;
    @Schema(description = "Минимальная допустимая толщина стенки")
    private Double minAcceptableThickness;
    @Schema(description = "Минимальная допустимая твердость металла элемента")
    @NotNull(message = "minHardness should not be null")
    @Positive(message = "minHardness can only be positive")
    private Integer minAcceptableHardness;
    @Schema(description = "Максимальная допустимая твердость металла элемента")
    @NotNull(message = "maxHardness should not be null")
    @Positive(message = "maxHardness can only be positive")
    private Integer maxAcceptableHardness;
    @Schema(description = "Допустимая погрешность измерения")
    @NotNull(message = "measurementError should not be null")
    @Positive(message = "measurementError can only be positive")
    private Float measurementError;

    @Override
    public String toString() {
        return "NewAcceptableMetalHardnessDto{" +
                "equipmentLibraryId=" + equipmentLibraryId +
                ", elementLibraryId=" + elementLibraryId +
                ", partElementLibraryId=" + partElementLibraryId +
                ", minAcceptableDiameter=" + minAcceptableDiameter +
                ", minAcceptableThickness=" + minAcceptableThickness +
                ", minAcceptableHardness=" + minAcceptableHardness +
                ", maxAcceptableHardness=" + maxAcceptableHardness +
                ", measurementError=" + measurementError +
                '}';
    }
}