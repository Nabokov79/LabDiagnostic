package ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для добавления участка трубопроводов тепловой сети")
public class NewHeatSupplySiteLibraryDto {

    @Schema(description = "Полное описание участка тепловой сети")
    @NotNull(message = "fullDescription should not be null")
    @NotBlank(message = "fullDescription should not be blank")
    private String fullDescription;
    @Schema(description = "Краткое описание участка тепловой сети")
    @NotBlank(message = "shortDescription should not be blank")
    @Max(value = 120, message = "shortDescription can't be more than 120")
    private String shortDescription;
    @Schema(description = "Идентификатор источника теплоснабжения")
    @NotNull(message = "source id should not be null")
    @Positive(message = "source id can only be positive")
    private Long sourceId;
}