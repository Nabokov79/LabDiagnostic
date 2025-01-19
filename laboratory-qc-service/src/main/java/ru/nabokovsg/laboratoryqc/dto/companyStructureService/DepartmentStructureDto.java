package ru.nabokovsg.laboratoryqc.dto.companyStructureService;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Информация о структуре лаборатории НК")
public class DepartmentStructureDto {

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
}