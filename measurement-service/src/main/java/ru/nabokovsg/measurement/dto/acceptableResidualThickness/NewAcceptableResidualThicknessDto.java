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
    @Schema(description = "Толщина элемента")
    private Double thickness;
    @Schema(description = "Минимальный диаметр")
    private Integer minDiameter;
    @Schema(description = "Толщина стенки минимального диаметра")
    private Double minThickness;
    @Schema(description = "Максимальный диаметр")
    private Integer maxDiameter;
    @Schema(description = "Толщина стенки максимального диаметра")
    private Double maxThickness;
    @Schema(description = "Минимальная допустимая толщина стенки элемента")
    @NotNull(message = "AcceptableThickness should not be null")
    @Positive(message = "AcceptableThickness can only be positive")
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
                ", minDiameter=" + minDiameter +
                ", minThickness=" + minThickness +
                ", maxDiameter=" + maxDiameter +
                ", maxThickness=" + maxThickness +
                ", acceptableThickness=" + acceptableThickness +
                ", acceptablePercent=" + acceptablePercent +
                ", measurementError=" + measurementError +
                '}';
    }
}