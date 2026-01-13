package ru.nabokovsg.referencebooks.dto.organizationLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для добавления организации")
public class NewOrganizationLibraryDto {

    @Schema(description = "Полное наименование организации")
    @NotNull(message = "fullName should not be null")
    @NotBlank(message = "fullName should not be blank")
    private String fullName;
    @Schema(description = "Краткое наименование организации")
    @NotNull(message = "shortName should not be null")
    @NotBlank(message = "shortName should not be blank")
    private String shortName;
}