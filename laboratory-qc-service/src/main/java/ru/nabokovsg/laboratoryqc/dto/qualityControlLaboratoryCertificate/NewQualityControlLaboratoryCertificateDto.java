package ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления информации об аттестации лаборатории")
public class NewQualityControlLaboratoryCertificateDto {

    @Schema(description = "Идентификатор подразделения")
    @NotNull(message = "Department id should not be null")
    @Positive(message = "Department id can only be positive")
    private Long departmentId;
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
        return "NewQualityControlLaboratoryCertificateDto{" +
                "departmentId=" + departmentId +
                ", documentName='" + documentName + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", dateIssue=" + dateIssue +
                ", validityPeriod=" + validityPeriod +
                ", organization='" + organization + '\'' +
                '}';
    }
}
