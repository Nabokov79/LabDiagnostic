package ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Рассчитанные значения геодезических измерений противоположных мест измерения")
public class ResponseCalculatingOppositePointDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Номер места проведения измерения")
    private Integer firstPlaceNumber;
    @Schema(description = "Номер противоположного места проведения измерения")
    private Integer secondPlaceNumber;
    @Schema(description = "Отклонение между местами измерения")
    private Integer deviation;
    @Schema(description = "Допустимость рассчитанного значения отклонения")
    private Boolean acceptableDifference;
}