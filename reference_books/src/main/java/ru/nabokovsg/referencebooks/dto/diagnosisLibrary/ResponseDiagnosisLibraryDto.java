package ru.nabokovsg.referencebooks.dto.diagnosisLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные вида диагностики")
public class ResponseDiagnosisLibraryDto {

    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Идентификатор типа оборудования")
    private Long equipmentId;
    @Schema(description = "Наименование диагностики")
    private String diagnosis;
    @Schema(description = "Типы выполняемых измерений при диагностики")
    private List<String> measurementsType;
}
