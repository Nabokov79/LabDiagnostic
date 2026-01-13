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
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.NewDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.UpdateDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.service.DiagnosisLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Справочник видов диагностики/контроля",
        description="API для работы с справочником видов диагностики и контроля качества сварных соединений")
public class DiagnosisLibraryController {

    private final DiagnosisLibraryService service;

    @Operation(summary = "Добавить наименование диагностики")
    @PostMapping("/diagnostic")
    public ResponseEntity<ResponseDiagnosisLibraryDto> save(
            @RequestBody @Valid
            @Parameter(name = "Наименование диагностики") NewDiagnosisLibraryDto diagnosisDto) {
        return ResponseEntity.ok().body(service.save(diagnosisDto));
    }

    @Operation(summary = "Изменение наименование диагностики")
    @PatchMapping("/diagnostic")
    public ResponseEntity<ResponseDiagnosisLibraryDto> update(
            @RequestBody @Valid
            @Parameter(name = "Наименование диагностики") UpdateDiagnosisLibraryDto diagnosisDto) {
        return ResponseEntity.ok().body(service.update(diagnosisDto));
    }

    @Operation(summary = "Получить наименование диагностики")
    @GetMapping("/diagnostic/{id}")
    public ResponseEntity<ResponseDiagnosisLibraryDto> get(@PathVariable @NotNull @Positive
                                                           @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все наименования диагностики")
    @GetMapping("/diagnostics")
    public ResponseEntity<List<ResponseDiagnosisLibraryDto>> getAll(
            @RequestParam(name = "name", required = false)
            @Parameter(description = "Наименование диагностики") String name) {
        return ResponseEntity.ok().body(service.getAll(name));
    }

    @Operation(summary = "Удалить наименование диагностики")
    @DeleteMapping("/diagnostic/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Диагностика удалена из справочника.");
    }
}