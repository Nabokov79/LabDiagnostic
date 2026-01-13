package ru.nabokovsg.referencebooks.dto.repairLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.UpdateMeasurementParameterLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные изменения способа ремонта")
public class UpdateRepairLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Наименование типа ремонта")
    @NotNull(message = "name should not be null")
    @NotBlank(message = "repairName should not be blank")
    private String name;
    @Schema(description = "Объединить наименование с измерением параметра")
    @NotNull(message = "withoutNamingParameter should not be null")
    private boolean withoutNamingParameter;
    @Schema(description = "Измеряемые параметры")
    private List<UpdateMeasurementParameterLibraryDto> measuredParameters;
}