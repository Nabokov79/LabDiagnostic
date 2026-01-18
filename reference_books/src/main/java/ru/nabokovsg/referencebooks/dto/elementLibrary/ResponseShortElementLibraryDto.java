package ru.nabokovsg.referencebooks.dto.elementLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные элемента оборудования")
public class ResponseShortElementLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование элемента")
    private String name;
    @Schema(description = "Габаритные размеры")
    private String dimensions;
    @Schema(description = "Типоразмер")
    private String standardSize;
}