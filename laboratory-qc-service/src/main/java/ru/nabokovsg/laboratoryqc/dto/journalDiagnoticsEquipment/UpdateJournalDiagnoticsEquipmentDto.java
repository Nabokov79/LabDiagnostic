package ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Данные для изменения записи журнала диагностики оборудования котельной, цтп")
public class UpdateJournalDiagnoticsEquipmentDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Дата обследования")
    private LocalDate date;
    @Schema(description = "Идентификатор эксплуатационного участка")
    @NotNull(message = "exploitation region id should not be null")
    @Positive(message = "exploitation region id can only be positive")
    private Long exploitationRegionId;
    @Schema(description = "Идентификатор адреса")
    @NotNull(message = "address id should not be null")
    @Positive(message = "address id can only be positive")
    private Long addressId;
    @Schema(description = "Идентификатор оборудования")
    @NotNull(message = "equipment id should not be null")
    @Positive(message = "equipment id can only be positive")
    private Long equipmentId;
    @Schema(description = "Идентификатор элемента оборудования")
    private Long elementId;
    @Schema(description = "Основание проведения работы(адресная программа, заявка, распоряжение руководителя)")
    @NotBlank(message = "task source should not be blank")
    private String taskSource;
    @Schema(description = "Комментарий")
    private String comment;
    @Schema(description = "Идентификаторы сотрудников лаборатории назначенных для выполнения работы")
    @NotEmpty(message = "laboratory employees ids should not be empty")
    private List<Long> employeesIds;
    @Schema(description = "Идентификатор типа документа")
    @NotNull(message = "document type id should not be null")
    @Positive(message = "document type id can only be positive")
    private Long documentTypeId;

    @Override
    public String toString() {
        return "UpdateJournalDiagnoticsEquipmentDto{" +
                "id=" + id +
                ", date=" + date +
                ", exploitationRegionId=" + exploitationRegionId +
                ", addressId=" + addressId +
                ", equipmentId=" + equipmentId +
                ", taskSource='" + taskSource + '\'' +
                ", comment='" + comment + '\'' +
                ", employeesIds=" + employeesIds +
                ", documentTypeId=" + documentTypeId +
                '}';
    }
}