package ru.nabokovsg.equipment.dto.partElementLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации о подэлементе")
public class UpdatePartElementLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "employee certificate id should not be null")
    @Positive(message = "employee certificate id can only be positive")
    private Long id;
    @Schema(description = "Идентификатор элемента")
    @NotNull(message = "element id should not be null")
    @Positive(message = "element id can only be positive")
    private Long elementLibraryId;
    @Schema(description = "Наименование подэлемента")
    @NotBlank(message = "partElementName should not be blank")
    private String partElementName;
}