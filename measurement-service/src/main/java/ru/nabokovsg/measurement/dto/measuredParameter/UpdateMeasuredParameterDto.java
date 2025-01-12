package ru.nabokovsg.measurement.dto.measuredParameter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения измеренного значения параметра дефекта")
public class UpdateMeasuredParameterDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Значение параметра")
    @NotNull(message = "value should not be null")
    @Positive(message = "value can only be positive")
    private Double value;
}