package ru.nabokovsg.referencebooks.dto.organizationLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.referencebooks.dto.branchLibrary.ResponseBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.ResponseShortBranchLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные организации")
public class ResponseOrganizationLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Полное наименование")
    private String fullName;
    @Schema(description = "Краткое наименование")
    private String shortName;
    @Schema(description = "Филиалы")
    private List<ResponseShortBranchLibraryDto> branches;
}