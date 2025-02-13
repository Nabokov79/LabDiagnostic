package ru.nabokovsg.measurement.dto.acceptableResidualThickness;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Данные для добавления допустимых толщин элементов оборудования")
public class NewAcceptableResidualThicknessDto {

    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "equipmentLibraryId should not be null")
    @Positive(message = "equipmentLibraryId can only be positive")
    private Long equipmentLibraryId;
    @Schema(description = "Идентификатор элемента оборудования")
    @NotNull(message = "elementLibraryId should not be null")
    @Positive(message = "elementLibraryId can only be positive")
    private Long elementLibraryId;
    @Schema(description = "Идентификатор подэлемента элемента оборудования")
    private Long partElementLibraryId;
    @Schema(description = "Минимальная допкстимая толщина элемента")
    private Double thickness;
    @Schema(description = "Минимальный допустимый диаметр элемента")
    private Integer diameter;
    @Schema(description = "Минимальная допустимая толщина стенки элемента")
    private Double acceptableThickness;
    @Schema(description = "Минимальная допустимая толщина стенки элемента в процентах")
    private Integer acceptablePercent;
    @Schema(description = "Допустимая погрешность измерения")
    @NotNull(message = "measurementError should not be null")
    @Positive(message = "measurementError can only be positive")
    private Float measurementError;

    @Override
    public String toString() {
        return "NewAcceptableResidualThicknessDto{" +
                "equipmentLibraryId=" + equipmentLibraryId +
                ", elementLibraryId=" + elementLibraryId +
                ", partElementLibraryId=" + partElementLibraryId +
                ", thickness=" + thickness +
                ", diameter=" + diameter +
                ", acceptableThickness=" + acceptableThickness +
                ", acceptablePercent=" + acceptablePercent +
                ", measurementError=" + measurementError +
                '}';
    }
}