package ru.nabokovsg.equipmentunit.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные типа оборудования")
public class EquipmentLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Полное наименование типа оборудования")
    private String fullName;
    @Schema(description = "Период стабилизации основания")
    private Integer periodStabilization;
    @Schema(description = "Диаметр")
    private Integer diameter;
    @Schema(description = "Длина")
    private Integer length;
    @Schema(description = "Высота")
    private Integer height;
    @Schema(description = "Ширина")
    private Integer width;
}