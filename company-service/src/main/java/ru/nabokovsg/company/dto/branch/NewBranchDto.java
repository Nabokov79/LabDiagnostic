package ru.nabokovsg.company.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового филиала организации")
public class NewBranchDto {

    @Schema(description = "Идентификатор организации")
    @NotNull(message = "organization id should not be blank")
    @Positive(message = "organization id must be positive")
    private Long organizationId;
    @Schema(description = "Полное название")
    @NotBlank(message = "branch should not be blank")
    private String fullName;
    @Schema(description = "Краткое название")
    @NotBlank(message = "short name branch organization should not be blank")
    private String shortName;
    @Schema(description = "Идентификатор адреса")
    @NotNull(message = "address id should not be blank")
    @Positive(message = "address id can only be positive")
    private Long addressId;
}