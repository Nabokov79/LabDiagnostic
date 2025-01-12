package ru.nabokovsg.laboratoryqc.dto.qualityControlJournal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Запись журнала выполненных работ по контролю качества")
public class ResponseQualityControlJournalDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Дата выполнения работы")
    private LocalDate date;
    @Schema(description = "Филиал организации")
    private String branch;
    @Schema(description = "Район теплоснабжения(только филиал тепловых сетей)")
    private String heatSupplyArea;
    @Schema(description = "Эксплуатационный участок(только филиал энергетических источников)")
    private String exploitationRegion;
    @Schema(description = "Адрес выполнения работы")
    private String address;
    @Schema(description = "Наименование контролируемого оборудования")
    private String equipmentName;
    @Schema(description = "Вид диагностики/контроля")
    private String diagnosisType;
    @Schema(description = "Вид оформленного документа")
    private String document;
    @Schema(description = "Методический документ")
    private String methodologicalDocument;
    @Schema(description = "Нормативный документ")
    private String regulatoryDocument;
    @Schema(description = "Номер документа")
    private Integer documentNumber;
    @Schema(description = "Комментарий")
    private String comment;
    @Schema(description = "Руководитель работ")
    private String chief;
    @Schema(description = "Исполнители работы")
    private String employees;
    @Schema(description = "Статус выполнения работы, контроля качества")
    private String status;
}