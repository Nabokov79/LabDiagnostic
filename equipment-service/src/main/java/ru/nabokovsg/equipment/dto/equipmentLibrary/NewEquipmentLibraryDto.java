package ru.nabokovsg.equipment.dto.equipmentLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.equipment.model.common.Copy;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления информации о виде оборудования")
public class NewEquipmentLibraryDto {

    @Schema(description = "Наименование")
    @NotBlank(message = "equipmentName should not be blank")
    private String equipmentName;
    @Schema(description = "Объем оборудования типа ёмкость")
    private Integer volume;
    @Schema(description = "Модель")
    private String model;
    @Schema(description = "Положение оборудования")
    private String orientation;
    @Schema(description = "Идентификатор вида оборудования")
    @NotNull(groups = {Copy.class}, message = "equipmentLibraryId should not be null")
    private Long equipmentLibraryId;
}