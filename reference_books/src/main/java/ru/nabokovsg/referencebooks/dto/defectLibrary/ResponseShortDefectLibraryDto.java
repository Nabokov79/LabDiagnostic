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
    @Schema(description = "Наименование дефекта")
    private String name;
    @Schema(description = "Измеряемые параметры дефекта")
    private String measurementParameters;
    @Schema(description = "Нормативно-технический документ")
    private String documentationLibrary;
    @Schema(description = "Оценка качества дефекта")
    private String qualityAssessment;
}