package ru.nabokovsg.document.dto.integration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные шаблона заголовка документа")
public class DocumentHeaderTemplateDto {

    @Schema(description = "Полное наименование организации")
    private String organizationFullName;
    @Schema(description = "Краткое наименование организации")
    private String organizationShortName;
    @Schema(description = "Полное наименование филиала")
    private String branchFullName;
    @Schema(description = "Краткое наименование филиала")
    private String branchShortName;
    @Schema(description = "Полное наименование подразделения")
    private String departmentFullName;
    @Schema(description = "Краткое наименование подразделения")
    private String departmentShortName;
    @Schema(description = "Полное наименование района теплоснабжения")
    private String heatSupplyAreaFullName;
    @Schema(description = "Краткое наименование района теплоснабжения")
    private String heatSupplyAreaShortName;
    @Schema(description = "Полное наименование эксплуатационного участка")
    private String exploitationRegionFullName;
    @Schema(description = "Краткое наименование эксплуатационного участка")
    private String exploitationRegionShortName;
}