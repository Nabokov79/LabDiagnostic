package ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные вида документа диагностики")
public class ResponseDiagnosisDocumentLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование документа")
    private String document;
    @Schema(description = "Заголовок документа")
    private String title;
}