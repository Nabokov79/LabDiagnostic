package ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления нормативно-технической документации")
public class NewRegulatoryDocumentationLibraryDto {

    @Schema(description = "Идентификаторы типов оборудования")
    private List<@Positive(message = "equipment ids can only be positive") Long> equipmentIds;
    @Schema(description = "Полное наименование документа")
    @NotNull(message = "fullName should not be null")
    @NotBlank(message = "fullName should not be blank")
    @Max(value = 120, message = "fullName can't be more than 120")
    private String fullName;
    @Schema(description = "Тип и номер документа")
    @NotNull(message = "document should not be null")
    @NotBlank(message = "document should not be blank")
    @Max(value = 120, message = "document can't be more than 120")
    private String document;
    @Schema(description = "Наименование документа")
    @NotNull(message = "documentName should not be null")
    @NotBlank(message = "documentName should not be blank")
    @Max(value = 240, message = "documentName can't be more than 240")
    private String documentName;
    @Schema(description = "Тип документа")
    @NotNull(message = "documentType should not be null")
    @NotBlank(message = "documentType should not be blank")
    private String documentType;
    @Schema(description = "Статус документа")
    @NotNull(message = "status should not be null")
    @NotBlank(message = "status should not be blank")
    private String documentStatus;
    @Schema(description = "Область распространения документа")
    @NotBlank(message = "areaDistribution should not be blank")
    private String areaDistribution;
}