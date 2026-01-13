package ru.nabokovsg.referencebooks.dto.organizationLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации об организации")
public class UpdateOrganizationLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Полное наименование организации")
    @NotNull(message = "fullName should not be null")
    @NotBlank(message = "fullName should not be blank")
    private String fullName;
    @Schema(description = "Краткое наименование организации")
    @NotNull(message = "shortName should not be null")
    @NotBlank(message = "shortName should not be blank")
    private String shortName;
}