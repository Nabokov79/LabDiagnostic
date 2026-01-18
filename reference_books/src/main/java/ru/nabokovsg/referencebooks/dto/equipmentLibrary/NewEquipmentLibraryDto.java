package ru.nabokovsg.referencebooks.dto.equipmentLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Данные для добавления информации о виде оборудования")
public class NewEquipmentLibraryDto {

    @Schema(description = "Полное наименование")
    @NotNull(message = "fullName should not be null")
    @NotBlank(message = "fullName should not be blank")
    private String fullName;
    @Schema(description = "Краткое наименование")
    @NotNull(message = "shortName should not be null")
    @NotBlank(message = "shortName should not be blank")
    private String shortName;
    @Schema(description = "Объем оборудования типа ёмкость")
    @Positive(message = "volume can only be positive")
    private Integer volume;
    @Schema(description = "Модель")
    @NotBlank(message = "model should not be blank")
    private String model;
    @Schema(description = "Период стабилизации основания")
    @Positive(message = "periodStabilization can only be positive")
    private Integer periodStabilization;
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