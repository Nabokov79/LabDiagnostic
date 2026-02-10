package ru.nabokovsg.referencebooks.dto.diagnosisLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные вида диагностики")
public class ResponseShortDiagnosisLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Тип оборудования")
    private String equipmentLibrary;
    @Schema(description = "Наименование диагностики")
    private String diagnosis;
    @Schema(description = "Типы выполняемых измерений при диагностики")
    private String measurements;
}