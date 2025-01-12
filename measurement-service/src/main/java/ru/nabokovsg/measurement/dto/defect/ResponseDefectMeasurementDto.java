package ru.nabokovsg.measurement.dto.defect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.measurement.dto.measuredParameter.ResponseMeasuredParameterDto;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные измеренного дефекта")
public class ResponseDefectMeasurementDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование элемента")
    private String elementName;
    @Schema(description = "Наименование подэлемента")
    private String partElementName;
    @Schema(description = "Наименование дефекта")
    private String defectName;
    @Schema(description = "Статус допустимости значений измерения дефекта")
    private String measurementStatus;
    @Schema(description = "Рассчитанные параметры дефекта")
    private Set<ResponseMeasuredParameterDto> measuredParameters;
}