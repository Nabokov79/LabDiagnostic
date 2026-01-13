package ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления нормативно-технической документации")
public class NewRegulatoryDocumentationLibraryDto {

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