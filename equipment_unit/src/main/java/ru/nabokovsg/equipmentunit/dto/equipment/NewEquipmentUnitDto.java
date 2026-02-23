package ru.nabokovsg.equipmentunit.dto.equipment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.equipmentunit.model.Device;
import ru.nabokovsg.equipmentunit.model.Source;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления единицы оборудования")
public class NewEquipmentUnitDto {

    @Schema(description = "Идентификатор источника теплоснабжения")
    @NotNull(groups = {Source.class}, message = "source id should not be null")
    @Positive(groups = {Source.class}, message = "source id can only be positive")
    private Long sourceId;
    @Schema(description = "Идентификатор технического устройства")
    @NotNull(groups = {Device.class}, message = "device id should not be null")
    @Positive(groups = {Device.class}, message = "device id can only be positive")
    private Long deviceId;
    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "equipmentLibrary id should not be null")
    @Positive(message = "equipmentLibrary id can only be positive")
    private Long equipmentLibraryId;
    @Schema(description = "Полное наименование")
    @NotNull(message = "fullName should not be null")
    @NotBlank(message = "fullName should not be blank")
    private String fullName;
    @Schema(description = "Краткое наименование")
    @NotNull(message = "shortName should not be null")
    @NotBlank(message = "shortName should not be blank")
    private String shortName;
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