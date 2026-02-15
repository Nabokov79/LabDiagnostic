package ru.nabokovsg.referencebooks.dto.metalHardnessLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные допустимых толщин элементов оборудования")
public class ResponseMetalHardnessLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор нормативно-технического документа")
    private Long documentationId;
    @Schema(description = "Идентификатор типа оборудования")
    private Long equipmentId;
    @Schema(description = "Идентификатор элемента оборудования")
    private Long elementId;
    @Schema(description = "Идентификатор подэлемента элемента оборудования")
    private Long partElementId;
    @Schema(description = "Минимальный допустимый диаметр")
    private Double diameter;
    @Schema(description = "Минимальная допустимая толщина стенки")
    private Double thickness;
    @Schema(description = "Минимальная допустимая твердость металла элемента")
    private Integer minAcceptableHardness;
    @Schema(description = "Максимальная допустимая твердость металла элемента")
    private Integer maxAcceptableHardness;
    @Schema(description = "Допустимая погрешность измерения")
    private Float measurementError;
}