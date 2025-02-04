package ru.nabokovsg.measurement.dto.repairMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
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
@Schema(description = "Данные для добавления результатов измерения мест ремонта элемента, подэлемента оборудования")
public class NewRepairMeasurementDto {

    @Schema(description = "Идентификатор диагностируемого оборудования")
    @NotNull(message = "equipmentId should not be null")
    @Positive(message = "equipmentId can only be positive")
    private Long equipmentId;
    @Schema(description = "Идентификатор элемента")
    @NotNull(message = "elementId should not be null")
    @Positive(message = "elementId can only be positive")
    private Long elementId;
    @Schema(description = "Идентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Идентификатор ремонта")
    @NotNull(message = "repairId should not be null")
    @Positive(message = "repairId can only be positive")
    private Long repairLibraryId;
    @Schema(description = "Измеренные параметры выполненного ремонта элемента")
    private List<@Valid NewMeasuredParameterDto> measuredParameters;
}