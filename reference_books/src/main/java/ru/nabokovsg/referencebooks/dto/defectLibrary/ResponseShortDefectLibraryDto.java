package ru.nabokovsg.referencebooks.dto.defectLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные дефекта")
public class ResponseShortDefectLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Тип оборудования")
    private String equipmentLibrary;
    @Schema(description = "Нормативно-технический документ")
    private String documentationLibrary;
    @Schema(description = "Наименование дефекта")
    private String name;
    @Schema(description = "Наименование дефекта")
    private String measuredParameters;
    @Schema(description = "Номинальная толщина стенки элементов")
    private String thickness;
    @Schema(description = "Оценочный участок в мм/%")
    private String assessmentArea;
    @Schema(description = "Суммарная длина в мм/%")
    private String totalLength;
    @Schema(description = "Количество дефектов")
    private Integer defectsQuantity;
    @Schema(description = "Оценка качества")
    private String qualityAssessment;
}