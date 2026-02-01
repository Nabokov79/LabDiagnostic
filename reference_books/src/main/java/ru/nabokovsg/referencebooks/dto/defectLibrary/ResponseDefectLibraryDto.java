package ru.nabokovsg.referencebooks.dto.defectLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.ResponseMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.model.QualityAssessment;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные дефекта")
public class ResponseDefectLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор типа оборудования")
    private Long equipmentLibraryId;
    @Schema(description = "Идентификатор нормативно-технического документа")
    private Long documentationLibraryId;
    @Schema(description = "Наименование дефекта")
    private String name;
    @Schema(description = "Объединить наименование с измерением параметра")
    private boolean withoutNamingParameter;
    @Schema(description = "Оценка качества")
    private QualityAssessment qualityAssessmentType;
    @Schema(description = "Оценочный участок в мм")
    private Double assessmentAreaMM;
    @Schema(description = "Оценочный участок в процентах")
    private Double assessmentAreaPercentage;
    @Schema(description = "Количество дефектов")
    private Integer defectsQuantity;
    @Schema(description = "Суммарная длина в мм")
    private Integer totalLengthMM;
    @Schema(description = "Суммарная длина в процентах")
    private Integer totalLengthPercentage;
    @Schema(description = "Минимальная диаметр элемента")
    private Float minDiameter;
    @Schema(description = "Максимальный диаметр элемента")
    private Float maxDiameter;
    @Schema(description = "Минимальная толщина элемента")
    private Float minThickness;
    @Schema(description = "Максимальная толщина элемента")
    private Float maxThickness;
    @Schema(description = "Измеряемые параметры")
    private List<ResponseMeasurementParameterLibraryDto> measuredParametersLibrary;
}