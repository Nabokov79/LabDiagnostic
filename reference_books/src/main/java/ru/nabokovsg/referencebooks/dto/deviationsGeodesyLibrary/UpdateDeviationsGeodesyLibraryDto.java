package ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения допустимых отклонений значений геодезических измерений")
public class UpdateDeviationsGeodesyLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "equipment id should not be null")
    @Positive(message = "equipment id can only be positive")
    private Long equipmentId;
    @Schema(description = "Идентификатор нормативно-технического документа")
    @NotNull(message = "documentation id should not be null")
    @Positive(message = "documentation id can only be positive")
    private Long documentationId;
    @Schema(description = "Наличие теплоносителя")
    @NotNull(message = "withHeatCarrier should not be null")
    private Boolean withHeatCarrier;
    @Schema(description = "Состояние оборудования: true=новое,  false=старое")
    @NotNull(message = "condition should not be null")
    private Boolean condition;
    @Schema(description = "Максимальная допустимая осадка")
    @Positive(message = "acceptablePrecipitation can only be positive")
    private Integer acceptablePrecipitation;
    @Schema(description = "Максимальная допустимая разность для соседних точек)")
    @NotNull(message = "maxDifferenceNeighboringPoints should not be null")
    @Positive(message = "maxDifferenceNeighboringPoints can only be positive")
    private Integer maxDifferenceNeighboringPoints;
    @Schema(description = "Максимальная допустимая разность для диаметральных точек")
    @NotNull(message = "maxDifferenceDiametricPoints should not be null")
    @Positive(message = "maxDifferenceDiametricPoints can only be positive")
    private Integer maxDifferenceDiametricPoints;
}