package ru.nabokovsg.measurement.dto.ultrasonicControl;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Результат ультразвукового контроля сварного соединения")
public class ResponseUltrasonicControlDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Порядковый номер сварного соединения")
    private Integer weldedJointNumber;
    @Schema(description = "Типоразмер соединяемых элементов")
    private String standardSize;
    @Schema(description = "Описание обнаруженного дефекта")
    private String descriptionDefect;
    @Schema(description = "Координаты расположения дефекта")
    private String coordinates;
    @Schema(description = "Оценка качества сварного соеднинения")
    private String qualityAssessment;
}