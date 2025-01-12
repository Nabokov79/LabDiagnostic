package ru.nabokovsg.company.dto.organization;

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
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для добавления организации")
public class NewOrganizationDto {

    @Schema(description = "Полное наименование организации")
    @NotBlank(message = "organization should not be blank")
    private String fullName;
    @Schema(description = "Краткое наименование организации")
    @NotBlank(message = "short name organization should not be blank")
    private String shortName;
    @Schema(description = "Идентификатор адреса")
    @NotNull(message = "address id should not be blank")
    @Positive(message = "address id can only be positive")
    private Long addressId;
}