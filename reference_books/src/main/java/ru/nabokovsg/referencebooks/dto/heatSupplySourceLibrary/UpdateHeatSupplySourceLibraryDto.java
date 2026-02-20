package ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Информация для изменения данных энергетических источников")
public class UpdateHeatSupplySourceLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Тип источника")
    @NotNull(message = "source should not be null")
    @NotBlank(message = "source should not be blank")
    private String source;
    @Schema(description = "Aдрес")
    @NotNull(message = "address should not be null")
    @NotBlank(message = "address should not be blank")
    @Max(value = 120, message = "address can't be more than 120")
    private String address;
}