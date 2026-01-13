package ru.nabokovsg.referencebooks.dto.partElementLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Информация о подэлементе о элемента оборудования")
public class ResponsePartElementLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование подэлемента")
    private String name;
    @Schema(description = "Место на подэлементе")
    private String place;
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