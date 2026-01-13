package ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary;

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
@Schema(description = "Данные для изменения информации нормативно-технической документации")
public class UpdateRegulatoryDocumentationLibraryDto {

    @Schema(description = "Идентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Вид документа")
    @NotNull(message = "view should not be null")
    @NotBlank(message = "view should not be blank")
    private String view;
    @Schema(description = "Номер документа")
    @NotNull(message = "number should not be null")
    @NotBlank(message = "number should not be blank")
    private String number;
    @Schema(description = "Заголовок документа")
    @NotNull(message = "title should not be null")
    @NotBlank(message = "title should not be blank")
    private String title;
}