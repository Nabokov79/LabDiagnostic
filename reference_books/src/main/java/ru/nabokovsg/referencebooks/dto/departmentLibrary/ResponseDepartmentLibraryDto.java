package ru.nabokovsg.referencebooks.dto.departmentLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.ResponseShortHeatSupplySourceLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные подразделения")
public class ResponseDepartmentLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String fullName;
    @Schema(description = "Краткое название")
    private String shortName;
    @Schema(description = "Источники теплоснабжения")
    private List<ResponseShortHeatSupplySourceLibraryDto> heatSupplySources;
}