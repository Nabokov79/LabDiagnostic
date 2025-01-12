package ru.nabokovsg.company.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.company.dto.branch.ResponseShortBranchDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные организации")
public class ResponseOrganizationDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Полное наименование")
    private String fullName;
    @Schema(description = "Краткое наименование")
    private String shortName;
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Филиалы")
    private List<ResponseShortBranchDto> branches;
}