package ru.nabokovsg.equipment.dto.integration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Сведения об единицы оборудования для расчета результата измерения")
public class EquipmentDto {

    @Schema(description = "Идентификатор типа оборудования")
    private Long equipmentLibraryId;
    @Schema(description = "Наименование элемента единицы оборудования")
    private String elementName;
    @Schema(description = "Идентификатор типа элемента")
    private Long elementLibraryId;
    @Schema(description = "Идентификатор типа подэлемента")
    private Long partElementLibraryId;
    @Schema(description = "Наименование подэлемента единицы оборудования")
    private String partElementName;
    @Schema(description = "Типоразмер элемента, подэлемента единицы оборудования")
    private String standardSize;
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
    @Schema(description = "Объем оборудования типа ёмкость")
    private Integer volume;
    @Schema(description = "Старый или новый бак-аккумулятор")
    private Boolean old;
    @Schema(description = "Количество мест проведения измерений геодезии")
    private Integer geodesyLocations;
    @Schema(description = "Наличие в оборудовании теплоносителя")
    boolean heatCarrier;
}