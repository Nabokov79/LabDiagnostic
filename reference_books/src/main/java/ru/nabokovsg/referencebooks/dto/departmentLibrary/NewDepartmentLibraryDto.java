package ru.nabokovsg.referencebooks.dto.departmentLibrary;

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
public class NewDepartmentLibraryDto {

    @Schema(description = "Идентификатор филиала")
    @NotNull(message = "branch id should not be blank")
    @Positive(message = "branch id must be positive")
    private Long branchId;
    @Schema(description = "Полное наименование подразделения")
    @NotNull(message = "fullName should not be null")
    @NotBlank(message = "fullName should not be blank")
    private String fullName;
    @Schema(description = "Краткое наименование подразделения")
    @NotNull(message = "shortName should not be null")
    @NotBlank(message = "shortName should not be blank")
    private String shortName;
}