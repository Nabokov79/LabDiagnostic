package ru.nabokovsg.referencebooks.dto.repairLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.ResponseMeasurementParameterLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Полные данные способа ремонта")
public class ResponseRepairLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование")
    private String name;
    @Schema(description = "Объединить наименование с измерением параметра")
    private boolean withoutNamingParameter;
    @Schema(description = "Измеряемые параметры")
    private List<ResponseMeasurementParameterLibraryDto> measuredParameters;
}
