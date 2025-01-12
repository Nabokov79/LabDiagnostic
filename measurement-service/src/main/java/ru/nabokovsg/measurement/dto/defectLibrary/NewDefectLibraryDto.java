package ru.nabokovsg.measurement.dto.defectLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.measurement.dto.measurementParameterLibrary.NewMeasurementParameterLibraryDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления информации о дефекте")
public class NewDefectLibraryDto {

    @Schema(description = "Наименование дефекта")
    @NotBlank(message = "defectName should not be blank")
    private String defectName;
    @Schema(description = "Указание недопустимости дефекта")
    @NotNull(message = "notMeetRequirements should not be null")
    private Boolean unacceptable;
    @Schema(description = "Использовать дефект для расчета остаточной толщины")
    @NotNull(message = "useCalculateThickness should not be null")
    private Boolean useCalculateThickness;
    @Schema(description = "Требуемые вычисления параметров дефекта")
    @NotBlank(message = "type document should not be blank")
    private String calculation;
    @Schema(description = "Измеряемые параметры дефекта")
    private List<@Valid NewMeasurementParameterLibraryDto> measuredParameters;

    @Override
    public String toString() {
        return "NewDefectLibraryDto{" +
                "defectName='" + defectName + '\'' +
                ", unacceptable=" + unacceptable +
                ", useCalculateThickness=" + useCalculateThickness +
                ", calculation='" + calculation + '\'' +
                ", measuredParameters=" + measuredParameters +
                '}';
    }
}