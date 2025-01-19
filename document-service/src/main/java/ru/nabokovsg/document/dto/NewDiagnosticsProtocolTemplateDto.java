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
@Schema(description = "Данные шаблона протокола по результатам диагностики")
public class NewDiagnosticsProtocolTemplateDto {

    @Schema(description = "Идентификатор типа документа")
    @NotNull(message = "DocumentType id should not be blank")
    @Positive(message = "DocumentType id must be positive")
    private Long documentTypeId;
    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "EquipmentLibrary id should not be blank")
    @Positive(message = "EquipmentLibrary id must be positive")
    private Long equipmentLibraryId;
    @Schema(description = "Описание места проведения измерений")
    @NotBlank(message = "LocationMeasurements should not be blank")
    private String locationMeasurements;
    @Schema(description = "Чертеж единицы оборудования с указанием мест выполнения измерений")
    private String appendices;
    @Schema(description = "Текст с указанием погрешности измерения")
    private String measurementError;
}