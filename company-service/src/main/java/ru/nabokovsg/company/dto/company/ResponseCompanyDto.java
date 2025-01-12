package ru.nabokovsg.company.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.heatSupplyArea.ResponseHeatSupplyAreaDto;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Данные структурного подразделения")
public class ResponseCompanyDto {

    @Schema(description = "Полное наименование филиала")
    private String branchFullName;
    @Schema(description = "Краткое наименование филиала")
    private String branchShortName;
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Районы теплоснабжения")
    private ResponseHeatSupplyAreaDto heatSupplyArea;
    @Schema(description = "Эксплуатационные участки")
    private ExploitationRegionDto exploitationRegion;
    @Schema(description = "Данные котельной, цтп")
    private BuildingDto building;
}