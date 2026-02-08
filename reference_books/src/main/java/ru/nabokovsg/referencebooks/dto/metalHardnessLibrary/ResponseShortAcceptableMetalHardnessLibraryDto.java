package ru.nabokovsg.referencebooks.dto.metalHardnessLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные твердости металла элементов оборудования")
public class ResponseShortAcceptableMetalHardnessLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Нормативно-технический документ")
    private String documentationLibrary;
    @Schema(description = "Полное наименование типа оборудования")
    private String equipmentFullName;
    @Schema(description = "Полное наименование элемента (элемент + подэлемент)")
    private String elementFullName;
    @Schema(description = "Типоразмер элемента(подэлемента)")
    private String standardSize;
    @Schema(description = "Минимальный  диаметр элемента, допустимый измерения твердости металла")
    private Double diameter;
    @Schema(description = "Минимальная толщина стенки элемента, допустимая для измерения твердости металла")
    private Double thickness;
    @Schema(description = "Минимальная допустимая твердость металла элемента")
    private Integer minAcceptableHardness;
    @Schema(description = "Максимальная допустимая твердость металла элемента")
    private Integer maxAcceptableHardness;
    @Schema(description = "Допустимая погрешность измерения")
    private Float measurementError;
}