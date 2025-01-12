package ru.nabokovsg.measurement.dto.repairLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.measurement.dto.measurementParameterLibrary.NewMeasurementParameterLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные добавления/изменения способа ремонта")
public class NewRepairLibraryDto {

    @Schema(description = "Наименование типа ремонта")
    @NotBlank(message = "repairName should not be blank")
    private String repairName;
    @Schema(description = "Требуемые вычисления параметров ремонта элемента")
    @NotBlank(message = "calculation should not be blank")
    private String calculation;
    @Schema(description = "Измеряемые параметры ремонта элемента")
    private List<@Valid NewMeasurementParameterLibraryDto> measuredParameters;

    @Override
    public String toString() {
        return "NewRepairLibraryDto{" +
                "repairName='" + repairName + '\'' +
                ", calculation='" + calculation + '\'' +
                ", measuredParameters=" + measuredParameters +
                '}';
    }
}