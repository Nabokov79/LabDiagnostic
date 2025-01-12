package ru.nabokovsg.laboratoryqc.dto.companyStructureService;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Данные структурного подразделения")
public class CompanyStructureDto {

    @Schema(description = "Полное наименование филиала")
    private String branchFullName;
    @Schema(description = "Краткое наименование филиала")
    private String branchShortName;
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Районы теплоснабжения")
    private HeatSupplyAreaDto heatSupplyArea;
    @Schema(description = "Эксплуатационные участки")
    private ExploitationRegionDto exploitationRegion;
    @Schema(description = "Данные котельной, цтп")
    private BuildingDto building;
}