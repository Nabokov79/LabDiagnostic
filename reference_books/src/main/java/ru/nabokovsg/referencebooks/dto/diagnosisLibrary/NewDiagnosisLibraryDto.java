package ru.nabokovsg.referencebooks.dto.diagnosisLibrary;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления вида диагностики")
public class NewDiagnosisLibraryDto {

    @Schema(description = "Идентификатор типа оборудования")
    @NotNull(message = "equipmentLibrary id should not be null")
    @Positive(message = "equipmentLibrary id can only be positive")
    private Long equipmentLibraryId;
    @Schema(description = "Наименование диагностики")
    @NotNull(message = "diagnosis should not be null")
    @NotBlank(message = "diagnosis should not be blank")
    private String diagnosis;
    @Schema(description = "Типы выполняемых измерений при диагностики")
    @NotNull(message = "measurementsType should not be null")
    @NotEmpty(message = "measurementsType should not be empty")
    private List<String> measurementsType;
}