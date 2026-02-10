package ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления вида документа диагностики")
public class NewDiagnosisDocumentLibraryDto {

    @Schema(description = "Наименование документа")
    @NotNull(message = "document should not be null")
    @NotBlank(message = "name should not be blank")
    private String document;
    @Schema(description = "Заголовок документа")
    @NotBlank(message = "title should not be blank")
    @NotNull(message = "title should not be null")
    private String title;
}