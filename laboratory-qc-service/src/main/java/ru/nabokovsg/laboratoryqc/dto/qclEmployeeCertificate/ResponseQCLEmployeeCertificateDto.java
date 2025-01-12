package ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные об аттестации сотрудника")
public class ResponseQCLEmployeeCertificateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип документа")
    private String documentName;
    @Schema(description = "Номер сертификата")
    private String documentNumber;
    @Schema(description = "Вид контроля соглано документа")
    private String controlName;
    @Schema(description = "Квалификационный уровень сотрудника по данным документа")
    private String level;
    @Schema(description = "Дата выдачи документа")
    private LocalDate dateIssue;
    @Schema(description = "Дата окончания действиядокумента")
    private LocalDate validityPeriod;
    @Schema(description = "Шифр объектов, для контроля которых допущен сотрудник согласно данным сертификата")
    private String points;
    @Schema(description = "Организация, выдавшая сертификат")
    private String organization;
}