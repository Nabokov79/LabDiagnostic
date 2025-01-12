package ru.nabokovsg.company.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.department.ResponseShortDepartmentDto;
import ru.nabokovsg.company.dto.exploitationRegion.ResponseExploitationRegionDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ResponseHeatSupplyAreaDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные филиала")
public class ResponseBranchDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Полное название")
    private String fullName;
    @Schema(description = "Краткое название")
    private String shortName;
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Подразделения")
    private List<ResponseShortDepartmentDto> departments;
    @Schema(description = "Районы теплоснабжения")
    private List<ResponseHeatSupplyAreaDto> heatSupplyAreas;
    @Schema(description = "Эксплуатационные участки")
    private List<ResponseExploitationRegionDto> exploitationRegions;
}