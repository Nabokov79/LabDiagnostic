package ru.nabokovsg.laboratoryqc.dto.qclDocumentType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового вида отчетного документа")
public class NewQCLDocumentTypeDto {

    @Schema(description = "Вид работы")
    @NotBlank(message = "Work type source should not be blank")
    private String workType;
    @Schema(description = "Вид документа")
    @NotBlank(message = "Type document should not be blank")
    private String type;
    @Schema(description = "Заголовок документа")
    @NotBlank(message = "Document title should not be blank")
    private String title;
}