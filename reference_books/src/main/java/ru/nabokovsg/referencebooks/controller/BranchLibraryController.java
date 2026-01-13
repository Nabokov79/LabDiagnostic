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
import ru.nabokovsg.referencebooks.dto.branchLibrary.NewBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.ResponseBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.ResponseShortBranchLibraryDto;
import ru.nabokovsg.referencebooks.dto.branchLibrary.UpdateBranchLibraryDto;
import ru.nabokovsg.referencebooks.service.BranchLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Филиал организации", description="API для работы с данными филиала")
public class BranchLibraryController {

    private final BranchLibraryService service;

    @Operation(summary = "Добавить данные филиала")
    @PostMapping("/branch")
    public ResponseEntity<ResponseShortBranchLibraryDto> save(@RequestBody @Valid
                                                       @Parameter(description = "Филиал") NewBranchLibraryDto branchDto) {
        return ResponseEntity.ok().body(service.save(branchDto));
    }

    @Operation(summary = "Изменить данные филиала")
    @PatchMapping("/branch")
    public ResponseEntity<ResponseShortBranchLibraryDto> update(@RequestBody @Valid
                                                         @Parameter(description = "Филиал") UpdateBranchLibraryDto branchDto) {
        return ResponseEntity.ok().body(service.update(branchDto));
    }

    @Operation(summary = "Получить данные филиала")
    @GetMapping("/branch/{id}")
    public ResponseEntity<ResponseBranchLibraryDto> get(@PathVariable @NotNull @Positive
                                                 @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные всех филиалов")
    @GetMapping("/branches/{id}")
    public ResponseEntity<List<ResponseShortBranchLibraryDto>> getAll(
                                                    @PathVariable(name = "id") @NotNull @Positive
                                                    @Parameter(description = "Идентификатор организации") Long id,
                                                    @RequestParam(name = "name", required = false)
                                                    @Parameter(description = "Наименование филиала") String name) {
        return ResponseEntity.ok().body(service.getAll(id, name));
    }

    @Operation(summary = "Удалить данные филиала")
    @DeleteMapping("/branch/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Филиал удален.");
    }
}