package ru.nabokovsg.referencebooks.dto.diagnosisLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления вида диагностики, обследования, контроля")
public class NewDiagnosisLibraryDto {

    @Schema(description = "Наименование диагностики, обследования, контроля")
    @NotNull(message = "diagnosis should not be null")
    @NotBlank(message = "diagnosis should not be blank")
    private String diagnosis;
    @Schema(description = "Наименование документа")
    @NotNull(message = "document should not be null")
    @NotBlank(message = "name should not be blank")
    private String document;
    @Schema(description = "Заголовок документа")
    @NotBlank(message = "title should not be blank")
    @NotNull(message = "title should not be null")
    private String title;
}