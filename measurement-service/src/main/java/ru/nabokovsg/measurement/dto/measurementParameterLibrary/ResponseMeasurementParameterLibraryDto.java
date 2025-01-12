package ru.nabokovsg.measurement.dto.measurementParameterLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Измеряемый параметр")
public class ResponseMeasurementParameterLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование параметра")
    private String parameterName;
    @Schema(description = "Максимальное допустимое значение параметра")
    private Double maxAllowedValue;
    @Schema(description = "Единица измерения параметра")
    private String unitMeasurement;
}