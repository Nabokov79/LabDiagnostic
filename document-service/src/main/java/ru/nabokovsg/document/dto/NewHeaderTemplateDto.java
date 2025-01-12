package ru.nabokovsg.document.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные для добавления шаблона заголовка документа")
public class NewHeaderTemplateDto {

    @Schema(description = "Идентификатор организации")
    @NotNull(message = "organization id should not be null")
    @Positive(message = "organization id can only be positive")
    private Long organizationId;
    @Schema(description = "Идентификатор филиала")
    @NotNull(message = "branch id should not be null")
    @Positive(message = "branch id can only be positive")
    private Long branchId;
    @Schema(description = "Идентификатор района теплоснабжения")
    private Long heatSupplyAreaId;
    @Schema(description = "Идентификатор подразделения")
    private Long departmentId;
    @Schema(description = "Идентификатор эксплуатационного участка")
    private Long exploitationRegionId;
}