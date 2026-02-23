package ru.nabokovsg.equipmentunit.dto.equipment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные единицы оборудования источника теплоснабжения")
public class ResponseEquipmentUnitSourceDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование филиала")
    private String branch;
    @Schema(description = "Наименование подразделения")
    private String department;
    @Schema(description = "Наименование источника теплоснабжения")
    private String heatSupplySource;
    @Schema(description = "Полное наименование единицы оборудования")
    private String fullName;
    @Schema(description = "Дата ввода в эксплуатацию")
    private LocalDate dateCommissioning;
    @Schema(description = "Расположение оборудования")
    private String room;
}