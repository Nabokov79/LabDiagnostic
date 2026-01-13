package ru.nabokovsg.referencebooks.dto.diagnosisLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные вида диагностики, обследования, контроля")
public class ResponseDiagnosisLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование диагностики, обследования, контроля")
    private String diagnosis;
    @Schema(description = "Наименование документа")
    private String document;
    @Schema(description = "Заголовок документа")
    private String title;
}