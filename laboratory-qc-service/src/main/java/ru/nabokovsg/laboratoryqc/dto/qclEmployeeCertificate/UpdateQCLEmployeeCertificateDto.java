package ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate;

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
@Schema(description = "Данные для изменения информации об аттестации сотрудника")
public class UpdateQCLEmployeeCertificateDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "employee certificate id should not be null")
    @Positive(message = "employee certificate id can only be positive")
    private Long id;
    @Schema(description = "Тип документа")
    @NotBlank(message = "document name should not be blank")
    private String documentName;
    @Schema(description = "Номер сертификата")
    @NotBlank(message = "Document number should not be blank")
    private String documentNumber;
    @Schema(description = "Вид контроля соглаcно документа")
    @NotBlank(message = "control type should not be blank")
    private String controlName;
    @Schema(description = "Квалификационный уровень сотрудника по данным документа")
    @NotBlank(message = "level should not be blank")
    private String level;
    @Schema(description = "Дата выдачи документа")
    @NotNull(message = "start date should not be null")
    private LocalDate dateIssue;
    @Schema(description = "Дата окончания действия документа")
    @NotNull(message = "Validity period should not be null")
    private LocalDate validityPeriod;
    @Schema(description = "Шифр объектов, для контроля которых допущен сотрудник согласно данным сертификата")
    @NotBlank(message = "points should not be blank")
    private String points;
    @Schema(description = "Организация, выдавшая сертификат")
    @NotBlank(message = "organization should not be blank")
    private String organization;
    @Schema(description = "Идентификатор сотрудника, которому принадлежит сертификат")
    @NotNull(message = "employee id should not be null")
    @Positive(message = "employee id can only be positive")
    private Long employeeId;
}