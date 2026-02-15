package ru.nabokovsg.referencebooks.dto.partElementLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о подэлементе")
public class UpdatePartElementLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Идентификатор элемента")
    @NotNull(message = "element id should not be null")
    @Positive(message = "element id can only be positive")
    private Long elementId;
    @Schema(description = "Наименование подэлемента")
    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be blank")
    private String name;
    @Schema(description = "Место на подэлементе")
    @NotBlank(message = "place should not be blank")
    private String place;
    @Schema(description = "Диаметр (габаритный размер)")
    @Positive(message = "diameter can only be positive")
    private Integer diameter;
    @Schema(description = "Длина (габаритный размер)")
    @Positive(message = "length can only be positive")
    private Integer length;
    @Schema(description = "Высота (габаритный размер)")
    @Positive(message = "height can only be positive")
    private Integer height;
    @Schema(description = "Ширина (габаритный размер)")
    @Positive(message = "width can only be positive")
    private Integer width;
    @Schema(description = "Диаметр (типоразмер)")
    @Positive(message = "diameter can only be positive")
    private Integer diameterSize;
    @Schema(description = "Толщина(типоразмер)")
    @Positive(message = "thickness can only be positive")
    private Double thicknessSize;
}