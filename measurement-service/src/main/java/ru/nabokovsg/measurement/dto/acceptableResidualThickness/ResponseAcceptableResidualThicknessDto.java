package ru.nabokovsg.measurement.dto.acceptableResidualThickness;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные допустимых толщин элементов оборудования")
public class ResponseAcceptableResidualThicknessDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор типа оборудования")
    private Long equipmentLibraryId;
    @Schema(description = "Идентификатор элемента оборудования")
    private Long elementLibraryId;
    @Schema(description = "Идентификатор подэлемента оборудования")
    private Long partElementLibraryId;
    @Schema(description = "Диаметр элемента")
    private Integer diameter;
    @Schema(description = "Толщина стенки элемента")
    private Double thickness;
    @Schema(description = "Минимальная допустимая толщина стенки элемента")
    private Double acceptableThickness;
    @Schema(description = "Минимальная допустимая толщина стенки элемента в процентах")
    private Integer acceptablePercent;
    @Schema(description = "Допустимая погрешность измерения")
    private Float measurementError;
}