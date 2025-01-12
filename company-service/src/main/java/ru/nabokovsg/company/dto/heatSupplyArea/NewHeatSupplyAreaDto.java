package ru.nabokovsg.company.dto.heatSupplyArea;

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
@Schema(description = "Новые данные района теплоснабжения")
public class NewHeatSupplyAreaDto {

    @Schema(description = "Идентификатор филиала")
    @NotNull(message = "branch id should not be blank")
    @Positive(message = "branch id must be positive")
    private Long branchId;
    @Schema(description = "Полное наименование")
    @NotBlank(message = "full name heat supply area should not be blank")
    private String fullName;
    @Schema(description = "Краткое наименование")
    @NotBlank(message = "short name  heat supply area should not be blank")
    private String shortName;
}