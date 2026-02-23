package ru.nabokovsg.equipmentunit.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные источника теплоснабжения")
public class HeatSupplySourceLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Тип источника")
    private String source;
    @Schema(description = "Адрес")
    private String address;
}