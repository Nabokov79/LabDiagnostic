package ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Рассчитанные значения отклонений по реперу")
public class ResponseDeviationYearDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Год выполнения измерений репера")
    private Integer year;
    @Schema(description = "Отклонение")
    private Integer deviation;
}