package ru.nabokovsg.measurement.dto.repairMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.measurement.dto.measuredParameter.ResponseMeasuredParameterDto;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные измерений ремонта элемента с измеренными параметрами")
public class ResponseRepairMeasurementDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование элемента")
    private String elementName;
    @Schema(description = "Наименование подэлемента")
    private String partElementName;
    @Schema(description = "Наименование ремонта")
    private String repairName;
    @Schema(description = "Рассчитанные параметры выполненного ремонта элемента")
    private Set<ResponseMeasuredParameterDto> measuredParameters;
}