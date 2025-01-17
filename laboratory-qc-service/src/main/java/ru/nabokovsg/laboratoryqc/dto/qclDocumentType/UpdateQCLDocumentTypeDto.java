package ru.nabokovsg.laboratoryqc.dto.qclDocumentType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о виде отчетного документа")
public class UpdateQCLDocumentTypeDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Вид диагностики/контроля")
    @NotBlank(message = "Diagnosis type source should not be blank")
    private String diagnosisType;
    @Schema(description = "Вид документа")
    @NotBlank(message = "Type document should not be blank")
    private String type;
    @Schema(description = "Заголовок документа")
    @NotBlank(message = "Document title should not be blank")
    private String documentTitle;
}