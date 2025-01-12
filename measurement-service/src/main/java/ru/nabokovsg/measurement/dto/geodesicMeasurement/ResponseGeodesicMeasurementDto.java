package ru.nabokovsg.measurement.dto.geodesicMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные результатов выполнения геодезии(нивелировании)")
public class ResponseGeodesicMeasurementDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Порядковый номер измерения")
    private Integer sequentialNumber;
    @Schema(description = "Номер места проведения измерения")
    private Integer numberMeasurementLocation;
    @Schema(description = "Измеренное значение высоты по реперу")
    private Integer referencePointValue;
    @Schema(description = "Измеренное значение высоты по контрольной точке")
    private Integer controlPointValue;
    @Schema(description = "Измеренное значение при смене положения прибора(переход)")
    private Integer transitionValue;
}