package ru.nabokovsg.equipment.dto.equipment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления информации об оборудовании")
public class NewEquipmentDto {

    @Schema(description = "Идентификатор адреса")
    @NotNull(message = "address id should not be null")
    @Positive(message = "address id can only be positive")
    private Long addressId;
    @Schema(description = "Идентификатор котельной, цтп")
    private Long buildingId;
    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "equipmentType id should not be null")
    @Positive(message = "equipmentType id can only be positive")
    private Long equipmentLibraryId;
    @Schema(description = "Стационарный номер")
    private Integer stationaryNumber;
    @Schema(description = "Старый или новый бак-аккумулятор")
    private Boolean old;
    @Schema(description = "Расположение оборудования")
    private String room;
    @Schema(description = "Количество мест проведения измерений геодезии")
    private Integer geodesyLocations;

    @Override
    public String toString() {
        return "NewEquipmentDto{" +
                ", addressId=" + addressId +
                ", buildingId=" + buildingId +
                ", equipmentLibraryId=" + equipmentLibraryId +
                ", stationaryNumber=" + stationaryNumber +
                ", old=" + old +
                ", room='" + room + '\'' +
                ", geodesyLocations=" + geodesyLocations +
                '}';
    }
}