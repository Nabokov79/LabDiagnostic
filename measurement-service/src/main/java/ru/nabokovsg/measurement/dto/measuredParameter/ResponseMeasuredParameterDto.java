package ru.nabokovsg.measurement.dto.measuredParameter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Измеряемый параметр")
public class ResponseMeasuredParameterDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование параметра")
    private String parameterName;
    @Schema(description = "Измеренное значение параметра")
    private Double value;
    @Schema(description = "Единица измерения параметра")
    private String unitMeasurement;
}