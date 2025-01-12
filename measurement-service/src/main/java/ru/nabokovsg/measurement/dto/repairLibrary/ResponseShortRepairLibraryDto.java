package ru.nabokovsg.measurement.dto.repairLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные способа ремонта")
public class ResponseShortRepairLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Наименование типа ремонта")
    private String repairName;
}