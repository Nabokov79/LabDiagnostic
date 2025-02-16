package ru.nabokovsg.measurement.dto.acceptableMetalHardness;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные допустимых толщин элементов оборудования")
public class ResponseAcceptableMetalHardnessDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор типа оборудования")
    private Long equipmentLibraryId;
    @Schema(description = "Идентификатор элемента оборудования")
    private Long elementLibraryId;
    @Schema(description = "Идентификатор подэлемента оборудования")
    private Long partElementLibraryId;
    @Schema(description = "Минимальный  диаметр элемента, допустимый измерения твердости металла")
    private Integer minAcceptableDiameter;
    @Schema(description = "Минимальная толщина стенки элемента, домустимая для измерения измерения твердости металла")
    private Double minAcceptableThickness;
    @Schema(description = "Минимальная допустимая твердость металла элемента")
    private Integer minAcceptableHardness;
    @Schema(description = "Максимальная допустимая твердость металла элемента")
    private Integer maxAcceptableHardness;
    @Schema(description = "Допустимая погрешность измерения")
    private Float measurementError;
}