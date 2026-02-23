package ru.nabokovsg.equipmentunit.dto.equipment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления единицы оборудования")
public class UpdateEquipmentUnitDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Стационарный номер")
    @Positive(message = "stationaryNumber can only be positive")
    private Integer stationaryNumber;
    @Schema(description = "Дата ввода в эксплуатацию")
    private LocalDate dateCommissioning;
    @Schema(description = "Количество мест проведения измерений геодезии")
    @Positive(message = "geodesyLocations can only be positive")
    private Integer geodesyLocations;
    @Schema(description = "Расположение оборудования")
    @NotBlank(message = "room should not be blank")
    private String room;
    @Schema(description = "Диаметр")
    @Positive(message = "diameter can only be positive")
    private Integer diameter;
    @Schema(description = "Длина")
    @Positive(message = "length can only be positive")
    private Integer length;
    @Schema(description = "Высота")
    @Positive(message = "height can only be positive")
    private Integer height;
    @Schema(description = "Ширина")
    @Positive(message = "width can only be positive")
    private Integer width;
}