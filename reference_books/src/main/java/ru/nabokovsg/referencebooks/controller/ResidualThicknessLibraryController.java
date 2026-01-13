package ru.nabokovsg.referencebooks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.NewResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.UpdateResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.service.ResidualThicknessLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные минимальных допустимых толщин стенок элементов оборудования",
        description="API для работы с данными норм минимальных допустимых стенок элементов оборудования")
public class ResidualThicknessLibraryController {

    private final ResidualThicknessLibraryService service;

    @Operation(summary = "Добавить значение допустимой толщины")
    @PostMapping("/thickness")
    public ResponseEntity<ResponseResidualThicknessLibraryDto> save(
                                                 @RequestBody @Valid @Parameter(name = "Значение допустимой толщины")
                                                 NewResidualThicknessLibraryDto thicknessDto) {
        return ResponseEntity.ok().body(service.save(thicknessDto));
    }

    @Operation(summary = "Изменение значение допустимой толщины")
    @PatchMapping("/thickness")
    public ResponseEntity<ResponseResidualThicknessLibraryDto> update(
                                                @RequestBody @Valid @Parameter(name = "Значение допустимой толщины")
                                                UpdateResidualThicknessLibraryDto thicknessDto) {
        return ResponseEntity.ok().body(service.update(thicknessDto));
    }

    @Operation(summary = "Получить допустимое значение толщины")
    @GetMapping("/thickness/{id}")
    public ResponseEntity<ResponseResidualThicknessLibraryDto> get(@PathVariable @NotNull @Positive
                                                                  @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все значения допустимых толщин")
    @GetMapping("/thicknesses/{id}")
    public ResponseEntity<List<ResponseResidualThicknessLibraryDto>> getAll(
            @PathVariable(name = "id") @NotNull @Positive
            @Parameter(description = "Идентификатор типа оборудования") Long equipmentLibraryId) {
        return ResponseEntity.ok().body(service.getAll(equipmentLibraryId));
    }

    @Operation(summary = "Удалить значение допустимой толщины")
    @DeleteMapping("/thickness/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Значение допустимой толщины удалено.");
    }
}