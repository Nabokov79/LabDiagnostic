package ru.nabokovsg.measurement.dto.acceptableDeviationsGeodesy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные допустимых отклонений геодезических измерений")
public class ResponseAcceptableDeviationsGeodesyDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор типа оборудования")
    private Long equipmentLibraryId;
    @Schema(description = "Полное или пустое оборудование")
    private Boolean full;
    @Schema(description = "Старое или новое оборудование")
    private Boolean old;
    @Schema(description = "Максимальная допустимая осадка")
    private Integer acceptablePrecipitation;
    @Schema(description = "Максимальная допустимая разность для соседних точек)")
    private Integer maxDifferenceNeighboringPoints;
    @Schema(description = "Максимальная допустимая разность для диаметральных точек")
    private Integer maxDifferenceDiametricPoints;
}