package ru.nabokovsg.measurement.dto.calculationMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Рассчитанные измерения ремонта элемента, подэлемента оборудования")
public class ResponseCalculationRepairMeasurementDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Идентификатор элемента оборудования")
    private Long elementId;
    @Schema(description = "Идентификатор подэлемента элемента")
    private Long partElementId;
    @Schema(description = "Наименование выполненного ремонта")
    private String repairName;
    @Schema(description = "Наименование элемента оборудования")
    private String elementName;
    @Schema(description = "Наименование подэлемента элемента")
    private String partElementName;
    @Schema(description = "Строка рассчитанных параметров ремонта")
    private String parametersString;
}