package ru.nabokovsg.equipment.dto.partElementLibrary;

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
    @NotBlank(message = "partElementName should not be blank")
    private String partElementName;
}