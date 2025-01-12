package ru.nabokovsg.equipment.dto.partElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные о подэлементе")
public class ResponsePartElementDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование подэлемента")
    private String partElementName;
    @Schema(description = "Типоразмер подэлемента")
    private String standardSize;
}