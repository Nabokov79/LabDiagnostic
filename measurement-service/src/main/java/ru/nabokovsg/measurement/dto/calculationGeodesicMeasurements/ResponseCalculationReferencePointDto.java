package ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Рассчитанные значения геодезических измерений по реперам")
public class ResponseCalculationReferencePointDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Номер места проведения измерения")
    private Integer numberMeasurementLocation;
    @Schema(description = "Рассчитанное значение высоты по реперу")
    private Integer referencePointValue;
    @Schema(description = "Отклонение по реперу")
    private Integer deviation;
    @Schema(description = "Значение осадки по реперу")
    private Integer precipitation;
    @Schema(description = "Значение допустимости осадки")
    private Boolean acceptablePrecipitation;
    @Schema(description = "Значения отклонений по реперу")
    private List<ResponseDeviationYearDto> deviationYeas;
}