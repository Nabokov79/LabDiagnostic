package ru.nabokovsg.equipment.dto.equipmentInspection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные обследования оборудования")
public class ResponseEquipmentInspectionDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Дата проведения обследования")
    private String date;
    @Schema(description = "Описание выполненного обследования")
    private String inspection;
    @Schema(description = "Номер документа, выданного по результатам обследования")
    private String documentNumber;
    @Schema(description = "Организация, выполнившая обследование")
    private String organization;
}