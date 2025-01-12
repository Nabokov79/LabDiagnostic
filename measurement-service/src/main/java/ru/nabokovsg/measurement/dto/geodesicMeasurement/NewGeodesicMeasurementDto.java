package ru.nabokovsg.measurement.dto.geodesicMeasurement;

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
@Schema(description = "Данные для добавления результатов измерений в одном месте проведения геодезии(нивелирования)")
public class NewGeodesicMeasurementDto {

    @Schema(description = "Идентификатор диагностируемого оборудования")
    @NotNull(message = "equipmentId should not be null")
    @Positive(message = "equipmentId can only be positive")
    private Long equipmentId;
    @Schema(description = "Номер места проведения измерения")
    @NotNull(message = "numberMeasurementLocation should not be null")
    @Positive(message = "numberMeasurementLocation can only be positive")
    private Integer numberMeasurementLocation;
    @Schema(description = "Измеренное значение высоты по реперу")
    private Integer referencePointValue;
    @Schema(description = "Измеренное значение высоты по контрольной точке")
    @NotNull(message = "controlPointValue should not be null")
    @Positive(message = "controlPointValue can only be positive")
    private Integer controlPointValue;
    @Schema(description = "Значение высоты, измеренное после смены положения прибора")
    private Integer transitionValue;

    @Override
    public String toString() {
        return "NewGeodesicMeasurementsDto{" +
                "equipmentId=" + equipmentId +
                ", numberMeasurementLocation=" + numberMeasurementLocation +
                ", referencePointValue=" + referencePointValue +
                ", controlPointValue=" + controlPointValue +
                ", transitionValue=" + transitionValue +
                '}';
    }
}