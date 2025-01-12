package ru.nabokovsg.equipment.dto.equipment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.equipment.dto.element.ResponseElementDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Полные данные оборудования")
public class ResponseEquipmentDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор типа оборудования")
    private Long equipmentLibraryId;
    @Schema(description = "Полное наименование")
    private String equipmentName;
    @Schema(description = "Стационарный номер")
    private Integer stationaryNumber;
    @Schema(description = "Объем")
    private Integer volume;
    @Schema(description = "Старое или новое оборудование")
    private Boolean old;
    @Schema(description = "Модель")
    private String model;
    @Schema(description = "Расположение оборудования")
    private String room;
    @Schema(description = "Количество мест проведения измерений геодезии")
    private Integer geodesyLocations;
    @Schema(description = "Элементы оборудования")
    private List<ResponseElementDto> elements;
}