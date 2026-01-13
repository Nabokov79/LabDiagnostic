package ru.nabokovsg.referencebooks.dto.metalHardnessLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные допустимых толщин элементов оборудования")
public class ResponseAcceptableMetalHardnessLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование элемента и подэлемента")
    private String elementName;
    @Schema(description = "Минимальный  диаметр элемента, допустимый измерения твердости металла")
    private Integer minAcceptableDiameter;
    @Schema(description = "Минимальная толщина стенки элемента, допустимая для измерения твердости металла")
    private Double minAcceptableThickness;
    @Schema(description = "Минимальная допустимая твердость металла элемента")
    private Integer minAcceptableHardness;
    @Schema(description = "Максимальная допустимая твердость металла элемента")
    private Integer maxAcceptableHardness;
    @Schema(description = "Допустимая погрешность измерения")
    private Float measurementError;
}