package ru.nabokovsg.referencebooks.dto.elementLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные элемента оборудования")
public class ResponseElementLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование элемента")
    private String name;
    @Schema(description = "Диаметр")
    private Integer diameter;
    @Schema(description = "Длина")
    private Integer length;
    @Schema(description = "Высота")
    private Integer height;
    @Schema(description = "Ширина")
    private Integer width;
    @Schema(description = "Толщина")
    private Double thickness;
}