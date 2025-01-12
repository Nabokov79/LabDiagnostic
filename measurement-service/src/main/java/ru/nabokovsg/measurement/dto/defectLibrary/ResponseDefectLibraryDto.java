package ru.nabokovsg.measurement.dto.defectLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.measurement.dto.measurementParameterLibrary.ResponseMeasurementParameterLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные дефекта")
public class ResponseDefectLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование дефекта")
    private String defectName;
    @Schema(description = "Указание недопустимости дефекта")
    private Boolean unacceptable;
    @Schema(description = "Использовать дефект для расчета остаточной толщины")
    private Boolean useCalculateThickness;
    @Schema(description = "Измеряемые параметры дефекта")
    private List<ResponseMeasurementParameterLibraryDto> measuredParameters;
}