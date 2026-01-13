package ru.nabokovsg.referencebooks.dto.elementLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.referencebooks.model.NewElement;

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
    @NotBlank(message = "element name should not be blank")
    @NotNull(message = "element name should not be null")
    private String name;
    @Schema(description = "Диаметр")
    @Positive(message = "diameter can only be positive")
    private Integer diameter;
    @Schema(description = "Длина")
    @Positive(message = "length can only be positive")
    private Integer length;
    @Schema(description = "Высота")
    @Positive(message = "height can only be positive")
    private Integer height;
    @Schema(description = "Ширина")
    @Positive(message = "width can only be positive")
    private Integer width;
    @Schema(description = "Толщина")
    @Positive(message = "thickness can only be positive")
    private Double thickness;
}