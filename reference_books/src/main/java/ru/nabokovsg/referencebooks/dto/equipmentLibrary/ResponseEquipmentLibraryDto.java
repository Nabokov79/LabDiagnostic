package ru.nabokovsg.referencebooks.dto.equipmentLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные вида оборудования")
public class ResponseEquipmentLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Полное наименование")
    private String fullName;
    @Schema(description = "Краткое наименование")
    private String shortName;
    @Schema(description = "Объем")
    private Integer volume;
    @Schema(description = "Модель")
    private String model;
    @Schema(description = "Диаметр")
    private Integer diameter;
    @Schema(description = "Длина")
    private Integer length;
    @Schema(description = "Высота")
    private Integer height;
    @Schema(description = "Ширина")
    private Integer width;
}