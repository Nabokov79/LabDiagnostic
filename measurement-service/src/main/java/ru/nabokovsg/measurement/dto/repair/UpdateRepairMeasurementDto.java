package ru.nabokovsg.measurement.dto.repair;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.measurement.dto.measuredParameter.UpdateMeasuredParameterDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Изменение результатов измерения мест ремонта элемента, подэлемента оборудования")
public class UpdateRepairMeasurementDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Измеренные параметры выполненного ремонта элемента")
    private List<@Valid UpdateMeasuredParameterDto> measuredParameters;
}