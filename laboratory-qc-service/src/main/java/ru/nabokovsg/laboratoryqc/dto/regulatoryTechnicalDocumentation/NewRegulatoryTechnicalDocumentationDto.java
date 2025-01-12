package ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления нормативно-технической документации")
public class NewRegulatoryTechnicalDocumentationDto {

    @Schema(description = "Вид документа")
    private String view;
    @Schema(description = "Номер документа")
    private String number;
    @Schema(description = "Заголовок документа")
    @NotBlank(message = "documentation title should not be blank")
    private String title;
}