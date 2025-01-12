package ru.nabokovsg.laboratoryqc.dto.journalDiagnosticsHeatSupply;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Запись журнала выполненных работ по диагностики оборудования тепловых сетей")
public class ResponseJournalDiagnosticsHeatSupplyDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Дата выполнения работы")
    private LocalDate date;
    @Schema(description = "Филиал организации")
    private String branch;
    @Schema(description = "Район теплоснабжения(только филиал тепловых сетей)")
    private String heatSupplyArea;
    @Schema(description = "Адрес выполнения работы")
    private String address;
    @Schema(description = "Наименование диагностируемого оборудования")
    private String equipmentName;
    @Schema(description = "Вид диагностики/контроля")
    private String diagnosisType;
    @Schema(description = "Вид оформленного документа")
    private String document;
    @Schema(description = "Номер документа")
    private Integer documentNumber;
    @Schema(description = "Основание проведения работы")
    private String taskSource;
    @Schema(description = "Комментарий")
    private String comment;
    @Schema(description = "Руководитель работ")
    private String chief;
    @Schema(description = "Исполнители работы")
    private String employees;
    @Schema(description = "Статус выполнения работы, результат проведения диагностики")
    private String status;
}