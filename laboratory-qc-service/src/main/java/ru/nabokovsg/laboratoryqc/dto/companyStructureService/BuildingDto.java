package ru.nabokovsg.laboratoryqc.dto.companyStructureService;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные котельной, цтп")
public class BuildingDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Тип строения")
    private String buildingType;
    @Schema(description = "Название")
    private String login;
    @Schema(description = "Адрес")
    private String address;
}