package ru.nabokovsg.laboratoryqc.dto.qclDocumentType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Данные вида отчетного документа")
public class ResponseQCLDocumentTypeDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Вид диагностики/контроля")
    private String diagnosisType;
    @Schema(description = "Вид документа")
    private String type;
    @Schema(description = "Заголовок документа")
    private String title;
}