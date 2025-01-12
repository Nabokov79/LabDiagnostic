package ru.nabokovsg.laboratoryqc.dto.journalCompletedWork;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Данные журнала выполненных работ")
public class JournalCompletedWorkDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Дата обследования")
    private LocalDate date;
    @Schema(description = "Идентификатор района теплоснабжения")
    private Long heatSupplyAreaId;
    @Schema(description = "Идентификатор эксплуатационного участка")
    private Long exploitationRegionId;
    @Schema(description = "Идентификатор адреса")
    private Long addressId;
    @Schema(description = "Идентификатор типа оборудования")
    private Long equipmentLibraryId;
    @Schema(description = "Идентификатор типа элемента оборудования")
    private Long elementLibraryId;
    @Schema(description = "Идентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Идентификатор элемента оборудования")
    private Long elementId;
    @Schema(description = "Основание проведения работы(адресная программа, заявка, распоряжение руководителя)")
    private String taskSource;
    @Schema(description = "Комментарий")
    private String comment;
    @Schema(description = "Идентификатор руководителя работ(сотрудник лаборатории)")
    private Long chiefId;
    @Schema(description = "Идентификаторы сотрудников лаборатории назначенных для выполнения работы")
    private List<Long> employeesIds;
    @Schema(description = "Идентификатор типа документа")
    private Long documentTypeId;
}