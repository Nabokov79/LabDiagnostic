package ru.nabokovsg.laboratoryqc.dto.integration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.ResponseQCLDocumentTypeDto;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Записи журнала выполненных работ")
public class JournalCompletedWorkDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Дата выполнения работы")
    private LocalDate date;
    @Schema(description = "Филиал организации")
    private String branch;
    @Schema(description = "Эксплуатационный участок(только филиал энергетических источников)")
    private String exploitationRegion;
    @Schema(description = "Район теплоснабжения(только филиал тепловых сетей)")
    private String heatSupplyArea;
    @Schema(description = "Адрес выполнения работы")
    private String address;
    @Schema(description = "Наименование диагностируемого оборудования")
    private String equipmentName;
    @Schema(description = "Вид диагностики/контроля")
    private String diagnosisType;
    @Schema(description = "Вид оформленного документа")
    private ResponseQCLDocumentTypeDto documentType;
    @Schema(description = "Номер документа")
    private Integer documentNumber;
    @Schema(description = "Руководитель работ")
    private String chief;
    @Schema(description = "Исполнители работы")
    private String employees;
}