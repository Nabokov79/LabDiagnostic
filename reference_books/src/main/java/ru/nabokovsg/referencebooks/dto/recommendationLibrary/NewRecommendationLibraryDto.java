package ru.nabokovsg.referencebooks.dto.recommendationLibrary;

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
@Schema(description = "Данные для добавления рекомендации по ремонту оборудования в справочник")
public class NewRecommendationLibraryDto {

    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "equipment id should not be null")
    @Positive(message = "equipment id can only be positive")
    private Long equipmentId;
    @Schema(description = "Рекомендация")
    @NotNull(message = "recommendation should not be null")
    @NotBlank(message = "recommendation should not be blank")
    private String recommendation;
}