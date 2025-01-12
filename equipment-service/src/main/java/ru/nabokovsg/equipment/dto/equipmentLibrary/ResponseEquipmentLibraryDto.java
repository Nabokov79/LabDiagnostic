package ru.nabokovsg.equipment.dto.equipmentLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.equipment.dto.elementLibrary.ResponseElementLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные вида оборудования")
public class ResponseEquipmentLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование")
    private String equipmentName;
    @Schema(description = "Объем")
    private Integer volume;
    @Schema(description = "Модель")
    private String model;
    @Schema(description = "Положение оборудования")
    private String orientation;
    @Schema(description = "Элементы оборудования")
    private List<ResponseElementLibraryDto> elements;
}