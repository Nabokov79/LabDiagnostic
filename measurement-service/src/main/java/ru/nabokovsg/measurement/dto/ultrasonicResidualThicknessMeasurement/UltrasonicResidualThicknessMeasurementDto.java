package ru.nabokovsg.measurement.dto.ultrasonicResidualThicknessMeasurement;

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
@Schema(description = "Данные для добавления результата ультразвукового измерения толщины стенки элемента" +
        ", подэлемента оборудования")
public class UltrasonicResidualThicknessMeasurementDto {

    @Schema(description = "Идентификатор оборудования")
    @NotNull(message = "equipmentId should not be null")
    @Positive(message = "equipmentId can only be positive")
    private Long equipmentId;
    @Schema(description = "Идентификатор элемента")
    @NotNull(message = "elementId should not be null")
    @Positive(message = "elementId can only be positive")
    private Long elementId;
    @Schema(description = "Идентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Номер измерения по схеме")
    @NotNull(message = "measurementNumber should not be null")
    @Positive(message = "measurementNumber can only be positive")
    private Integer measurementNumber;
    @Schema(description = "Минимальное измеренное значение")
    @NotNull(message = "minMeasurementValue should not be null")
    @Positive(message = "minMeasurementValue can only be positive")
    private Double minMeasurementValue;
    @Schema(description = "Максимальное измеренное значение")
    @NotNull(message = "maxMeasurementValue should not be null")
    @Positive(message = "maxMeasurementValue can only be positive")
    private Double maxMeasurementValue;
}