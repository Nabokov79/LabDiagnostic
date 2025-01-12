package ru.nabokovsg.measurement.dto.acceptableMetalHardness;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные допустимых значение твердости металла элементов оборудования")
public class AcceptableMetalHardnessDto {

    @Schema(description = "Идентификатор типа оборудования")
    private Long equipmentLibraryId;
    @Schema(description = "Идентификатор элемента оборудования")
    private Long elementLibraryId;
    @Schema(description = "Идентификатор подэлемента оборудования")
    private Long partElementLibraryId;
    @Schema(description = "Минимальный допустимый диаметр")
    private Integer minAcceptableDiameter;
    @Schema(description = "Минимальная допустимая толщина стенки")
    private Double minAcceptableThickness;
    @Schema(description = "Минимальная допустимая твердость металла элемента")
    private Integer minAcceptableHardness;
    @Schema(description = "Максимальная допустимая твердость металла элемента")
    private Integer maxAcceptableHardness;
    @Schema(description = "Допустимая погрешность измерения")
    private Float measurementError;
}