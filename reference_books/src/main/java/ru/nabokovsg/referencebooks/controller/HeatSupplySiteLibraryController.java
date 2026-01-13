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
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.NewHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.ResponseHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.ResponseShortHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.UpdateHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.service.HeatSupplySiteLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Участок тепловой сети", description="API для работы с данными участка тепловой сети")
public class HeatSupplySiteLibraryController {

    private final HeatSupplySiteLibraryService service;

    @Operation(summary = "Добавить данные участка тепловой сети")
    @PostMapping("/region")
    public ResponseEntity<ResponseShortHeatSupplySiteLibraryDto> save(@RequestBody @Valid
                                                              @Parameter(description = "Участок тепловой сети")
                                                          NewHeatSupplySiteLibraryDto siteDto) {
        return ResponseEntity.ok().body(service.save(siteDto));
    }

    @Operation(summary = "Изменить данные участка тепловой сети")
    @PatchMapping("/region")
    public ResponseEntity<ResponseShortHeatSupplySiteLibraryDto> update(@RequestBody @Valid
                                                                   @Parameter(description = "Участок тепловой сети")
                                                                   UpdateHeatSupplySiteLibraryDto siteDto) {
        return ResponseEntity.ok().body(service.update(siteDto));
    }

    @Operation(summary = "Получить данные участка тепловой сети")
    @GetMapping("/region/{id}")
    public ResponseEntity<ResponseHeatSupplySiteLibraryDto> get(@PathVariable @NotNull @Positive
                                                                @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные всех участков тепловой сети")
    @GetMapping("/regions/{id}")
    public ResponseEntity<List<ResponseShortHeatSupplySiteLibraryDto>> getAll(@PathVariable(name = "id") @NotNull @Positive
                                                         @Parameter(description = "Идентификатор подразделения")
                                                                                                  Long id
                                                       , @RequestParam(name = "name", required = false)
                                                         @Parameter(description = "Описание по паспорту, " +
                                                              "краткое описание участка тепловой сети") String name) {
        return ResponseEntity.ok().body(service.getAll(id, name));
    }

    @Operation(summary = "Удалить данные участка тепловой сети")
    @DeleteMapping("/region/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Участок тепловой сети удален.");
    }
}