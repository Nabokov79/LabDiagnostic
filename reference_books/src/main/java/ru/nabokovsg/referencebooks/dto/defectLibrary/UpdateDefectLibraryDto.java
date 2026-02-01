package ru.nabokovsg.referencebooks.dto.defectLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.MeasurementParameterLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о дефекте")
public class UpdateDefectLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "equipmentLibrary id should not be null")
    @Positive(message = "equipmentLibrary id can only be positive")
    private Long equipmentLibraryId;
    @Schema(description = "Идентификатор нормативно-технического документа")
    @NotNull(message = "documentationLibraryId should not be null")
    @Positive(message = "documentationLibraryId can only be positive")
    private Long documentationLibraryId;
    @Schema(description = "Наименование дефекта")
    @NotNull(message = "defectName should not be null")
    @NotBlank(message = "defectName should not be blank")
    private String name;
    @Schema(description = "Объединить наименование дефекта с измерением параметра")
    @NotNull(message = "withoutNamingParameter should not be null")
    private boolean withoutNamingParameter;
    @Schema(description = "Оценка качества")
    @NotBlank(message = "qualityAssessment should not be blank")
    private String qualityAssessment;
    @Schema(description = "Оценочный участок в мм")
    @Positive(message = "assessmentAreaMM can only be positive")
    private Double assessmentAreaMM;
    @Schema(description = "Оценочный участок в процентах")
    @Positive(message = "assessmentAreaMM can only be positive")
    @Max(value = 100, message = "assessmentAreaPercentage can't be more than 100")
    private Double assessmentAreaPercentage;
    @Schema(description = "Суммарная длина в мм")
    @Positive(message = "totalLengthMM can only be positive")
    private Double totalLengthMM;
    @Schema(description = "Суммарная длина в процентах")
    @Positive(message = "totalLengthPercentage can only be positive")
    @Max(value = 100, message = "totalLengthPercentage can't be more than 100")
    private Double totalLengthPercentage;
    @Schema(description = "Количество дефектов")
    @Positive(message = "defectsQuantity can only be positive")
    private Integer defectsQuantity;
    @Schema(description = "Минимальная диаметр элемента")
    @Positive(message = "minDiameter can only be positive")
    private Float minDiameter;
    @Schema(description = "Максимальный диаметр элемента")
    @Positive(message = " maxDiameter can only be positive")
    private Float maxDiameter;
    @Schema(description = "Минимальная толщина элемента")
    @Positive(message = "minThickness can only be positive")
    private Float minThickness;
    @Schema(description = "Максимальная толщина элемента")
    @Positive(message = " maxThickness can only be positive")
    private Float maxThickness;
    @Schema(description = "Измеряемые параметры")
    private List<MeasurementParameterLibraryDto> measuredParametersLibrary;
}