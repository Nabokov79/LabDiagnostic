package ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary;

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
@Schema(description = "Данные нового технического устройства")
public class NewTechnicalDeviceLibraryDto {

    @Schema(description = "Идентификатор участка тепловой сети")
    @NotNull(message = "site id should not be blank")
    @Positive(message = "site id must be positive")
    private Long siteId;
    @Schema(description = "Полное наименование")
    @NotNull(message = "fullName should not be null")
    @NotBlank(message = "fullName should not be blank")
    private String fullName;
    @Schema(description = "Краткое наименование")
    @NotNull(message = "shortName should not be null")
    @NotBlank(message = "shortName should not be blank")
    private String shortName;
}