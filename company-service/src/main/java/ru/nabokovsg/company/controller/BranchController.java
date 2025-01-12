package ru.nabokovsg.company.controller;

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
import ru.nabokovsg.company.dto.branch.NewBranchDto;
import ru.nabokovsg.company.dto.branch.ResponseBranchDto;
import ru.nabokovsg.company.dto.branch.ResponseShortBranchDto;
import ru.nabokovsg.company.dto.branch.UpdateBranchDto;
import ru.nabokovsg.company.service.BranchService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/company/branch",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Филиал организации",
        description="API для работы с данными филиала")
public class BranchController {

    private final BranchService service;

    @Operation(summary = "Добавление данных филиала")
    @PostMapping
    public ResponseEntity<ResponseShortBranchDto> save(@RequestBody @Valid
                                                       @Parameter(description = "Филиал") NewBranchDto branchDto) {
        return ResponseEntity.ok().body(service.save(branchDto));
    }

    @Operation(summary = "Изменение данных филиала")
    @PatchMapping
    public ResponseEntity<ResponseShortBranchDto> update(@RequestBody @Valid
                                                         @Parameter(description = "Филиал") UpdateBranchDto branchDto) {
        return ResponseEntity.ok().body(service.update(branchDto));
    }

    @Operation(summary = "Получить данные филиала во его идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBranchDto> get(@PathVariable @NotNull @Positive
                                                 @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение сведений о филиалах")
    @GetMapping
    public ResponseEntity<List<ResponseShortBranchDto>> getAll(
                                                       @RequestParam(name = "id", required = false)
                                                       @Parameter(description = "Идентификатор организации") Long id,
                                                       @RequestParam(name = "name", required = false)
                                                       @Parameter(description = "Наименование филиала") String name) {
        return ResponseEntity.ok().body(service.getAll(id, name));
    }

    @Operation(summary = "Удаление данных филиала")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Филиал успешно удален.");
    }
}