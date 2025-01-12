package ru.nabokovsg.measurement.dto.ultrasonicControl;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Добавить результат ультразвукового контроля сварного соединения")
public class NewUltrasonicControlDto {

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
    @Schema(description = "Описание обнаруженного дефекта")
    private String descriptionDefect;
    @Schema(description = "Координаты расположения дефекта")
    private String coordinates;
    @Schema(description = "Оценка качества сварного соеднинения")
    @NotBlank(message = "quality assessment should not be blank")
    private String qualityAssessment;
}