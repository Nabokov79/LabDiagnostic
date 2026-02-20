package ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary;

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
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные новых энергетических источников")
public class NewHeatSupplySourceLibraryDto {

    @Schema(description = "Тип источника")
    @NotNull(message = "source should not be null")
    @NotBlank(message = "source should not be blank")
    private String source;
    @Schema(description = "Aдрес")
    @NotNull(message = "address should not be null")
    @NotBlank(message = "address should not be blank")
    @Max(value = 120, message = "address can't be more than 120")
    private String address;
    @Schema(description = "Идентификатор подразделения")
    @NotNull(message = "department id should not be null")
    @Positive(message = "department id can only be positive")
    private Long departmentId;
}