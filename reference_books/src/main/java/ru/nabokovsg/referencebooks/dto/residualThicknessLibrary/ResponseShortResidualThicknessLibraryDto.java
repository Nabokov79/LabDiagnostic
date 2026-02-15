package ru.nabokovsg.referencebooks.dto.residualThicknessLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные допустимых толщин элементов оборудования")
public class ResponseShortResidualThicknessLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Нормативно-технический документ")
    private String documentation;
    @Schema(description = "Полное наименование типа оборудования")
    private String equipmentFullName;
    @Schema(description = "Полное наименование элемента (элемент + подэлемент)")
    private String elementFullName;
    @Schema(description = "Типоразмер элемента(подэлемента)")
    private String standardSize;
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