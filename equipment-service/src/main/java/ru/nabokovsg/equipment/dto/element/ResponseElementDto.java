package ru.nabokovsg.equipment.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.equipment.dto.partElement.ResponsePartElementDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные элемента оборудования")
public class ResponseElementDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование элемента")
    private String elementName;
    @Schema(description = "Типоразмер элемента")
    private String standardSize;
    @Schema(description = "Подэлементы элемента")
    private List<ResponsePartElementDto> partsElement;
}