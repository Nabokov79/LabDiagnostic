package ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Рассчитанные значения геодезических измерений по контрольным точкам")
public class ResponseCalculationControlPointDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Номер места проведения измерения")
    private Integer numberMeasurementLocation;
    @Schema(description = "Рассчитанное значение высоты по контрольной точке")
    private Integer controlPointValue;
    @Schema(description = "Отклонение по реперу")
    private Integer deviation;
}