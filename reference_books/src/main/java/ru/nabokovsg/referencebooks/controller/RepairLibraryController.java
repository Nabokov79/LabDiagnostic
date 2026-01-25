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
import ru.nabokovsg.referencebooks.dto.repairLibrary.NewRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseShortRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.UpdateRepairLibraryDto;
import ru.nabokovsg.referencebooks.service.RepairLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Справочник видов ремонтов элементов и подэлементов оборудования",
        description="API для работы с справочником видов ремонтов элементов и подэлементов оборудования")
public class RepairLibraryController {

    private final RepairLibraryService service;

    @Operation(summary = "Добавление способа ремонта")
    @PostMapping("/repair")
    public ResponseEntity<ResponseShortRepairLibraryDto> save(
            @RequestBody @Valid @Parameter(description = "Тип ремонта") NewRepairLibraryDto repairDto) {
        return ResponseEntity.ok().body(service.save(repairDto));
    }

    @Operation(summary = "Изменение данных способа ремонта")
    @PatchMapping("/repair")
    public ResponseEntity<ResponseShortRepairLibraryDto> update(
            @RequestBody @Valid @Parameter(description = "Тип ремонта") UpdateRepairLibraryDto repairDto) {
        return ResponseEntity.ok().body(service.update(repairDto));
    }

    @Operation(summary = "Получить тип ремонта")
    @GetMapping("/repair/{id}")
    public ResponseEntity<ResponseRepairLibraryDto> get(@PathVariable @NotNull @Positive
                                                        @Parameter(name = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить типы ремонта элементов оборудования по его типу")
    @GetMapping("/repairs")
    public ResponseEntity<List<ResponseShortRepairLibraryDto>> getAll(
                                                          @RequestParam(name = "name", required = false) String name) {
        return ResponseEntity.ok().body(service.getAll(name));
    }

    @Operation(summary = "Удалить тип ремонта")
    @DeleteMapping("/repair/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Тип ремонта элемента удален.");
    }
}