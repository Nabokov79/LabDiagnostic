package ru.nabokovsg.referencebooks.dto.recommendationLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные рекомендации по ремонту оборудования из справочника")
public class ResponseRecommendationLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор типа оборудования")
    private Long equipmentLibraryId;
    @Schema(description = "Тип оборудования")
    private String equipmentLibrary;
    @Schema(description = "Рекомендация")
    private String recommendation;
}