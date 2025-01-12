package ru.nabokovsg.measurement.dto.acceptableDeviationsGeodesy;

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
@Schema(description = "Данные для добавления/изменения значений норм " +
        "допустимых отклонений значений геодезических измерений")
public class NewAcceptableDeviationsGeodesyDto {

    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "equipmentTypeId should not be null")
    @Positive(message = "equipmentTypeId can only be positive")
    private Long equipmentLibraryId;
    @Schema(description = "Полное или пустое оборудование: true=полное,  false=пустое")
    @NotNull(message = "full should not be null")
    private Boolean full;
    @Schema(description = "Старое или новое оборудование: true=новое,  false=старое")
    @NotNull(message = "old should not be null")
    private Boolean old;
    @Schema(description = "Объем оборудования")
    @NotNull(message = "volume should not be null")
    @Positive(message = "volume can only be positive")
    private Integer volume;
    @Schema(description = "Максимальная допустимая осадка")
    private Integer acceptablePrecipitation;
    @Schema(description = "Максимальная допустимая разность для соседних точек)")
    @NotNull(message = "maxDifferenceNeighboringPoints should not be null")
    @Positive(message = "maxDifferenceNeighboringPoints can only be positive")
    private Integer maxDifferenceNeighboringPoints;
    @Schema(description = "Максимальная допустимая разность для диаметральных точек")
    @NotNull(message = "maxDifferenceDiametricPoints should not be null")
    @Positive(message = "maxDifferenceDiametricPoints can only be positive")
    private Integer maxDifferenceDiametricPoints;

    @Override
    public String toString() {
        return "NewAcceptableDeviationsGeodesyDto{" +
                "equipmentLibraryId=" + equipmentLibraryId +
                ", full=" + full +
                ", old=" + old +
                ", volume=" + volume +
                ", acceptablePrecipitation=" + acceptablePrecipitation +
                ", maxDifferenceNeighboringPoints=" + maxDifferenceNeighboringPoints +
                ", maxDifferenceDiametricPoints=" + maxDifferenceDiametricPoints +
                '}';
    }
}