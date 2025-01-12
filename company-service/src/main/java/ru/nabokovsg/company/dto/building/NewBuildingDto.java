package ru.nabokovsg.company.dto.building;

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
@Schema(description = "Данные новых энергетических источников")
public class NewBuildingDto {

    @Schema(description = "Тип строения")
    @NotBlank(message = "building type should not be blank")
    private String buildingType;
    @Schema(description = "Название")
    private String login;
    @Schema(description = "Идентификатор участка теплоснабжения")
    @NotNull(message = "exploitation region id should not be null")
    @Positive(message = "exploitation region id can only be positive")
    private Long exploitationRegionId;
    @Schema(description = "Идентификатор адреса")
    @NotNull(message = "address id should not be blank")
    @Positive(message = "address id can only be positive")
    private Long addressId;
}