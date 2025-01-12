package ru.nabokovsg.company.dto.department;

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
@Schema(description = "Данные нового подразделения филиала")
public class NewDepartmentDto {

    @Schema(description = "Идентификатор филиала")
    @NotNull(message = "branch id should not be blank")
    @Positive(message = "branch id must be positive")
    private Long branchId;
    @Schema(description = "Полное наименование подразделения")
    @NotBlank(message = "branch should not be blank")
    private String fullName;
    @Schema(description = "Краткое наименование подразделения")
    private String shortName;
    @Schema(description = "Идентификатор адреса")
    private Long addressId;
}