package ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные нормативно-технической документации")
public class ResponseShortRegulatoryDocumentationLibraryDto {

    @Schema(description = "Идентификатор")
    private long id;
    @Schema(description = "Полное наименование документа")
    private String fullName;
    @Schema(description = "Тип и номер документа")
    private String document;
    @Schema(description = "Наименование документа")
    private String documentName;
    @Schema(description = "Тип документа")
    private String documentType;
    @Schema(description = "Статус документа")
    private String documentStatus;
}