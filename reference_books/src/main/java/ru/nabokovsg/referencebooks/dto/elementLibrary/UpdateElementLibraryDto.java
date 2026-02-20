package ru.nabokovsg.referencebooks.dto.elementLibrary;

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
@Schema(description = "Данные для изменения элемента оборудования")
public class UpdateElementLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Наименование элемента")
    @NotNull(message = "element name should not be null")
    @NotBlank(message = "element name should not be blank")
    @Max(value = 120, message = "name can't be more than 120")
    private String name;
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