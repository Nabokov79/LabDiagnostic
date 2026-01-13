package ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary;

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
@Schema(description = "Данные для изменения участка трубопроводов тепловой сети")
public class UpdateHeatSupplySiteLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Полное описание участка тепловой сети")
    @NotNull(message = "fullDescription should not be null")
    @NotBlank(message = "fullDescription should not be blank")
    private String fullDescription;
    @Schema(description = "Краткое описание участка тепловой сети")
    @NotBlank(message = "shortDescription should not be blank")
    private String shortDescription;
}