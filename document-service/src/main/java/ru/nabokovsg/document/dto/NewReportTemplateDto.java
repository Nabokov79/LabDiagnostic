package ru.nabokovsg.document.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные шаблона отчета по результатам диагностики")
public class NewReportTemplateDto {

    @Schema(description = "Идентификатор типа документа")
    @NotNull(message = "DocumentType id should not be blank")
    @Positive(message = "DocumentType id must be positive")
    private Long documentTypeId;
    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "EquipmentLibrary id should not be blank")
    @Positive(message = "EquipmentLibrary id must be positive")
    private Long equipmentLibraryId;
    @Schema(description = "Основание и цель проведения работы")
    @NotBlank(message = "PurposeWork should not be blank")
    private String purposeWork;
}