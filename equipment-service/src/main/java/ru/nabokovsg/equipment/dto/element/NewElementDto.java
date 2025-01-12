package ru.nabokovsg.equipment.dto.element;

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
@Schema(description = "Данные для добавления информации об элементе к данным оборудования")
public class NewElementDto {

    @Schema(description = "Идентификатор диагностируемого оборудования")
    @NotNull(message = "equipmentDiagnosed id should not be null")
    @Positive(message = "equipmentDiagnosed id can only be positive")
    private Long equipmentId;
    @Schema(description = "Идентификатор элемента типа оборудования")
    @NotNull(message = "element id should not be null")
    @Positive(message = "element id can only be positive")
    private Long elementLibraryId;
    @Schema(description = "Идентификатор подэлемента типа оборудования")
    private Long partElementLibraryId;
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

    @Override
    public String toString() {
        return "NewEquipmentElementDto{" +
                "equipmentId=" + equipmentId +
                ", elementLibraryId=" + elementLibraryId +
                ", partElementLibraryId=" + partElementLibraryId +
                ", thickness=" + thickness +
                ", minDiameter=" + minDiameter +
                ", minThickness=" + minThickness +
                ", maxDiameter=" + maxDiameter +
                ", maxThickness=" + maxThickness +
                '}';
    }
}