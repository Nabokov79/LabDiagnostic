package ru.nabokovsg.referencebooks.dto.residualThicknessLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные допустимых толщин элементов оборудования")
public class ResponseResidualThicknessLibraryDto {

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
    @Schema(description = "Диаметр элемента(подэлемента)")
    private Double diameter;
    @Schema(description = "Толщина элемента(подэлемента)")
    private Double thickness;
    @Schema(description = "Минимальная допустимая толщина стенки элемента в мм")
    private Double  minAcceptableThicknessMM;
    @Schema(description = "Минимальная допустимая толщина стенки элемента в процентах")
    private Integer minAcceptableThicknessPercent;
    @Schema(description = "Максимальное допустимое утонение стенки элемента в мм")
    private Double  maxAcceptableThinningMM;
    @Schema(description = "Максимальное допустимое утонение стенки элемента в процентах")
    private Integer maxAcceptableThinningPercent;
    @Schema(description = "Допустимая погрешность измерения")
    private Float measurementError;
}
