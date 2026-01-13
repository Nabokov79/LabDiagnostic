package ru.nabokovsg.referencebooks.dto.repairLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные способа ремонта")
public class ResponseShortRepairLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование типа ремонта")
    private String name;
    @Schema(description = "Измеряемые параметры дефекта")
    private String measurementParameters;
}