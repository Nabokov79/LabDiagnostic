package ru.nabokovsg.equipment.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации об элементе к данным оборудования")
public class UpdateElementDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Идентификатор подэлемента оборудования")
    private Long partElementId;
    @Schema(description = "Толщина стенки")
    private Double thickness;
    @Schema(description = "Минимальный диаметр(для тройника, перехода)")
    private Integer minDiameter;
    @Schema(description = "Толщина стенки минимального диаметра")
    private Double minThickness;
    @Schema(description = "Максимальный диаметр(для тройника, перехода)")
    private Integer maxDiameter;
    @Schema(description = "Толщина стенки максимального диаметра")
    private Double maxThickness;
}