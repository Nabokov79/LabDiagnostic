package ru.nabokovsg.laboratoryqc.dto.journalDiagnosticsHeatSupply;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления записи в журнал диагностики оборудования тепловой сети")
public class NewJournalDiagnosticsHeatSupplyDto {

    @Schema(description = "Дата обследования")
    private LocalDate date;
    @Schema(description = "Идентификатор района теплоснабжения")
    @NotNull(message = "heat supply area id should not be null")
    @Positive(message = "heat supply area id can only be positive")
    private Long heatSupplyAreaId;
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
        return "NewJournalDiagnosticsHeatSupplyDto{" +
                "date=" + date +
                ", heatSupplyAreaId=" + heatSupplyAreaId +
                ", addressId=" + addressId +
                ", equipmentId=" + equipmentId +
                ", elementId=" + elementId +
                ", taskSource='" + taskSource + '\'' +
                ", comment='" + comment + '\'' +
                ", employeesIds=" + employeesIds +
                ", documentTypeId=" + documentTypeId +
                '}';
    }
}