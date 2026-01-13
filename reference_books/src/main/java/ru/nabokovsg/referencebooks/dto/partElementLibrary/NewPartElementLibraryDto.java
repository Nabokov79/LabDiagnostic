package ru.nabokovsg.referencebooks.dto.partElementLibrary;

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
@Schema(description = "Данные для добавления информации о подэлементе")
public class NewPartElementLibraryDto {

    @Schema(description = "Идентификатор элемента")
    @NotNull(message = "element id should not be null")
    @Positive(message = "element id can only be positive")
    private Long elementLibraryId;
    @Schema(description = "Наименование подэлемента")
    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be blank")
    private String name;
    @Schema(description = "Место на подэлементе")
    @NotBlank(message = "place should not be blank")
    private String place;
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