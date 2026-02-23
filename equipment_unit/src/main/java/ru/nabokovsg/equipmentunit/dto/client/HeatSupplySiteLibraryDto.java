package ru.nabokovsg.equipmentunit.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные участка тепловой сети")
public class HeatSupplySiteLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Полное описание участка тепловой сети")
    private String fullDescription;
    @Schema(description = "Краткое описание участка тепловой сети")
    private String shortDescription;
}