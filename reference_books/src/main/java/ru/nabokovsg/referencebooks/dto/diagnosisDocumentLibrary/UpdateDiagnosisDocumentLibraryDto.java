package ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о виде документа диагностики")
public class UpdateDiagnosisDocumentLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Наименование документа")
    @NotNull(message = "document should not be null")
    @NotBlank(message = "name should not be blank")
    @Max(value = 60, message = "fullName can't be more than 60")
    private String document;
    @Schema(description = "Заголовок документа")
    @NotNull(message = "title should not be null")
    @NotBlank(message = "title should not be blank")
    @Max(value = 240, message = "title can't be more than 240")
    private String title;
}