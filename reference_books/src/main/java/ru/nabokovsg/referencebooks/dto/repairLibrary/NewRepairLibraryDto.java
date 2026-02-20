package ru.nabokovsg.referencebooks.dto.repairLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.MeasurementParameterLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные добавления/изменения способа ремонта")
public class NewRepairLibraryDto {

    @Schema(description = "Наименование типа ремонта")
    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be blank")
    @Max(value = 60, message = "name can't be more than 60")
    private String name;
    @Schema(description = "Объединить наименование с измерением параметра")
    @NotNull(message = "withoutNamingParameter should not be null")
    private boolean withoutNamingParameter;
    @Schema(description = "Измеряемые параметры")
    private List<MeasurementParameterLibraryDto> measuredParametersLibrary;
}