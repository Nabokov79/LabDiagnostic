package ru.nabokovsg.equipmentunit.dto.equipment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Полные данные оборудования")
public class ResponseEquipmentUnitDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Стационарный номер")
    private Integer stationaryNumber;
    @Schema(description = "Дата ввода в эксплуатацию")
    private LocalDate dateCommissioning;
    @Schema(description = "Количество мест проведения измерений геодезии")
    private Integer geodesyLocations;
    @Schema(description = "Расположение оборудования")
    private String room;
    @Schema(description = "Диаметр")
    private Integer diameter;
    @Schema(description = "Длина")
    private Integer length;
    @Schema(description = "Высота")
    private Integer height;
    @Schema(description = "Ширина")
    private Integer width;
}