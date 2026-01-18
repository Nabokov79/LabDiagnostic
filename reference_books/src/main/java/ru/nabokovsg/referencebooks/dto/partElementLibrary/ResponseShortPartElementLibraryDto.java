package ru.nabokovsg.referencebooks.dto.partElementLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные подэлемента элемента оборудования")
public class ResponseShortPartElementLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование подэлемента")
    private String name;
    @Schema(description = "Место на подэлементе")
    private String place;
    @Schema(description = "Габаритные размеры")
    private String dimensions;
    @Schema(description = "Типоразмер")
    private String standardSize;
}