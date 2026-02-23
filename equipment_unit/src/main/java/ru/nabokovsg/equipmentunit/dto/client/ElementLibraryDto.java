package ru.nabokovsg.equipmentunit.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные элемента оборудования")
public class ElementLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование элемента")
    private String name;
    @Schema(description = "Диаметр (габаритный размер)")
    private Integer diameter;
    @Schema(description = "Длина (габаритный размер)")
    private Integer length;
    @Schema(description = "Высота (габаритный размер)")
    private Integer height;
    @Schema(description = "Ширина (габаритный размер)")
    private Integer width;
    @Schema(description = "Диаметр (типоразмер)")
    private Integer diameterSize;
    @Schema(description = "Толщина(типоразмер)")
    private Double thicknessSize;
}
