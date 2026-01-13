package ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные допустимых отклонений геодезических измерений")
public class ResponseDeviationsGeodesyLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наличие теплоносителя")
    private String heatCarrier;
    @Schema(description = "Старое или новое оборудование")
    private String equipmentCondition;
    @Schema(description = "Максимальная допустимая осадка")
    private Integer acceptablePrecipitation;
    @Schema(description = "Максимальная допустимая разность для соседних точек)")
    private Integer maxDifferenceNeighboringPoints;
    @Schema(description = "Максимальная допустимая разность для диаметральных точек")
    private Integer maxDifferenceDiametricPoints;
}