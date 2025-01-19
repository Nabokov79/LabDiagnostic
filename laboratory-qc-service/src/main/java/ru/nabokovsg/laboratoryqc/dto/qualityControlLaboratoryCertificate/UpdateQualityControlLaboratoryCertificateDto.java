package ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные изменения сведений об аттестации лаборатории")
public class UpdateQualityControlLaboratoryCertificateDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Наименование документа")
    @NotBlank(message = "document type should not be blank")
    private String documentName;
    @Schema(description = "Номер документа")
    private String documentNumber;
    @Schema(description = "Дата выдачи документа")
    @NotNull(message = "Date issue should not be null")
    private LocalDate dateIssue;
    @Schema(description = "Срок действия документа")
    @NotNull(message = "Validity period should not be null")
    private LocalDate validityPeriod;
    @Schema(description = "Организация, выдавшая документ")
    @NotBlank(message = "organization should not be blank")
    private String organization;

    @Override
    public String toString() {
        return "UpdateQualityControlLaboratoryCertificateDto{" +
                "id=" + id +
                ", documentName='" + documentName + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", dateIssue=" + dateIssue +
                ", validityPeriod=" + validityPeriod +
                ", organization='" + organization + '\'' +
                '}';
    }
}