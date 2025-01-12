package ru.nabokovsg.equipment.dto.equipment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации об оборудовании")
public class UpdateEquipmentDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "document header id should not be null")
    @Positive(message = "document header id can only be positive")
    private Long id;
    @Schema(description = "Идентификатор адреса")
    @NotNull(message = "address id should not be null")
    @Positive(message = "address id can only be positive")
    private Long addressId;
    @Schema(description = "Идентификатор котельной, цтп")
    private Long buildingId;
    @Schema(description = "Стационарный номер")
    private Integer stationaryNumber;
    @Schema(description = "Старый или новый бак-аккумулятор")
    private Boolean old;
    @Schema(description = "Производственное помещение")
    private String room;
    @Schema(description = "Количество мест проведения измерений геодезии")
    private Integer geodesyLocations;

    @Override
    public String toString() {
        return "UpdateEquipmentDto{" +
                "id=" + id +
                ", addressId=" + addressId +
                ", buildingId=" + buildingId +
                ", stationaryNumber=" + stationaryNumber +
                ", old=" + old +
                ", room='" + room + '\'' +
                ", geodesyLocations=" + geodesyLocations +
                '}';
    }
}