package ru.nabokovsg.equipment.dto.elementLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.equipment.dto.partElementLibrary.ResponsePartElementLibraryDto;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные элемента оборудования")
public class ResponseElementLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование")
    private String elementName;
    @Schema(description = "Данные подэлементов")
    private Set<ResponsePartElementLibraryDto> partsElement;
}