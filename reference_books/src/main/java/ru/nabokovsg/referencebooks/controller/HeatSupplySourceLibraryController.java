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
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.NewHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.ResponseHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.ResponseShortHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.UpdateHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.service.HeatSupplySourceLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Источник теплоснабжения",
        description="API для работы с информацией об источнике теплоснабжения(котельная, ЦТП)")
public class HeatSupplySourceLibraryController {

    private final HeatSupplySourceLibraryService service;

    @Operation(summary = "Добавить данные источника теплоснабжения")
    @PostMapping("/source")
    public ResponseEntity<ResponseShortHeatSupplySourceLibraryDto> save(@RequestBody @Valid
                                                                 @Parameter(description = "Источник теплоснабжения")
                                                            NewHeatSupplySourceLibraryDto sourceDto) {
        return ResponseEntity.ok().body(service.save(sourceDto));
    }

    @Operation(summary = "Изменить данные источника теплоснабжения")
    @PatchMapping("/source")
    public ResponseEntity<ResponseShortHeatSupplySourceLibraryDto> update(@RequestBody @Valid
                                                                   @Parameter(description = "Источник теплоснабжения")
                                                                     UpdateHeatSupplySourceLibraryDto sourceDto) {
        return ResponseEntity.ok().body(service.update(sourceDto));
    }

    @Operation(summary = "Получить данные источника теплоснабжения")
    @GetMapping("/source/{id}")
    public ResponseEntity<ResponseHeatSupplySourceLibraryDto> get(@PathVariable @NotNull @Positive
                                                 @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные всех источников теплоснабжения")
    @GetMapping("/sources/{id}")
    public ResponseEntity<List<ResponseShortHeatSupplySourceLibraryDto>> getAll(
          @PathVariable(name = "id") @NotNull @Positive @Parameter(description = "Идентификатор подразделения") Long id
        , @RequestParam(name = "source", required = false)
          @Parameter(description = "Источник теплоснабжения или его адрес") String source) {
        return ResponseEntity.ok().body(service.getAll(id, source));
    }

    @Operation(summary = "Удалить данные источника теплоснабжения")
    @DeleteMapping("/source/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Источник теплоснабжения удален.");
    }
}