package ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные аттестации лаборатории")
public class ResponseQualityControlLaboratoryCertificateDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование документа")
    private String documentName;
    @Schema(description = "Номер документа")
    private String documentNumber;
    @Schema(description = "Дата выдачи документа")
    private LocalDate dateIssue;
    @Schema(description = "Срок действия документа")
    private LocalDate validityPeriod;
    @Schema(description = "Организация, выдавшая документ")
    private String organization;
}
