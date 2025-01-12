package ru.nabokovsg.measurement.dto.weldDefectControl;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.measurement.dto.measuredParameter.NewMeasuredParameterDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Добавить результаты измерения дефекта сварного соединения")
public class NewWeldDefectControlDto {

    @Schema(description = "Идентификатор оборудования")
    @NotNull(message = "equipmentId should not be null")
    @Positive(message = "equipmentId can only be positive")
    private Long equipmentId;
    @Schema(description = "Порядковый номер сварного соединения")
    @NotNull(message = "weldedJointNumber should not be null")
    @Positive(message = "weldedJointNumber can only be positive")
    private Integer weldedJointNumber;
    @Schema(description = "Типоразмер соединяемых элементов")
    @NotBlank(message = "standardSize should not be blank")
    private String standardSize;
    @Schema(description = "Идентификатор дефекта")
    private Long defectId;
    @Schema(description = "Координаты расположения дефекта")
    private String coordinates;
    @Schema(description = "Оценка качества сварного соеднинения")
    private String qualityAssessment;
    @Schema(description = "Параметры дефекта элемента")
    private List<NewMeasuredParameterDto> measuredParameters;
}