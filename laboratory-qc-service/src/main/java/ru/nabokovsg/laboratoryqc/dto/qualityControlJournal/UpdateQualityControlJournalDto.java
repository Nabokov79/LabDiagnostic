package ru.nabokovsg.laboratoryqc.dto.qualityControlJournal;

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
@Schema(description = "Данные для изменения записи журнала диагностики оборудования тепловой сети")
public class UpdateQualityControlJournalDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Дата обследования")
    private LocalDate date;
    @Schema(description = "Идентификатор района теплоснабжения")
    private Long heatSupplyAreaId;
    @Schema(description = "Идентификатор эксплуатационного участка")
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
    @Schema(description = "Комментарий")
    private String comment;
    @Schema(description = "Идентификаторы сотрудников лаборатории назначенных для выполнения работы")
    @NotEmpty(message = "laboratory employees ids should not be empty")
    private List<Long> employeesIds;
    @Schema(description = "Идентификаторы измерительных инструментов")
    private List<Long>  measuringInstrumentsIds;
    @Schema(description = "Идентификатор типа документа")
    @NotNull(message = "document type id should not be null")
    @Positive(message = "document type id can only be positive")
    private Long documentTypeId;

    @Schema(description = "Методический документ")
    @NotBlank(message = "methodologicalDocument should not be blank")
    private String methodologicalDocument;
    @Schema(description = "Нормативный документ")
    @NotBlank(message = "regulatoryDocument should not be blank")
    private String regulatoryDocument;
    @Schema(description = "Наименование сварочного формуляра")
    private String appendices;

    @Override
    public String toString() {
        return "UpdateQualityControlJournalDto{" +
                "id=" + id +
                ", date=" + date +
                ", heatSupplyAreaId=" + heatSupplyAreaId +
                ", exploitationRegionId=" + exploitationRegionId +
                ", addressId=" + addressId +
                ", equipmentId=" + equipmentId +
                ", elementId=" + elementId +
                ", comment='" + comment + '\'' +
                ", employeesIds=" + employeesIds +
                ", documentTypeId=" + documentTypeId +
                ", methodologicalDocument=" + methodologicalDocument +
                ", regulatoryDocument=" + regulatoryDocument +
                ", appendices=" + appendices +
                '}';
    }
}