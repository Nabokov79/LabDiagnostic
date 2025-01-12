package ru.nabokovsg.measurement.dto.weldDefectControl;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.measurement.dto.measuredParameter.ResponseMeasuredParameterDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Изменить результаты измерения дефекта сварного соединения")
public class ResponseWeldDefectControlDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Порядковый номер сварного соединения")
    private Integer weldedJointNumber;
    @Schema(description = "Типоразмер соединяемых элементов")
    private String standardSize;
    @Schema(description = "Идентификатор дефекта")
    private Long defectId;
    @Schema(description = "Координаты расположения дефекта")
    private String coordinates;
    @Schema(description = "Оценка качества сварного соеднинения")
    private String qualityAssessment;
    @Schema(description = "Измеренные параметры дефекта элемента")
    private List<ResponseMeasuredParameterDto> measuredParameters;
}