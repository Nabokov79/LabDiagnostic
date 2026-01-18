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
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseShortElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.NewEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseShortEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.UpdateEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.service.EquipmentLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Справочник видов оборудования",
        description="API для работы с справочником видов оборудования")
public class EquipmentLibraryController {

    private final EquipmentLibraryService service;

    @Operation(summary = "Добавление нового вид оборудования")
    @PostMapping("/equipment")
    public ResponseEntity<ResponseShortEquipmentLibraryDto> save(
            @RequestBody @Valid @Parameter(description = "Вид оборудования") NewEquipmentLibraryDto equipmentDto) {
        return ResponseEntity.ok().body(service.save(equipmentDto));
    }

    @Operation(summary = "Изменение данных вида оборудования")
    @PatchMapping("/equipment")
    public ResponseEntity<ResponseShortEquipmentLibraryDto> update(
            @RequestBody @Valid @Parameter(description = "Вида оборудования") UpdateEquipmentLibraryDto equipmentDto) {
        return ResponseEntity.ok().body(service.update(equipmentDto));
    }

    @Operation(summary = "Копировать элементы")
    @GetMapping("/equipment/{id}/copy/{copyId}")
    public ResponseEntity<List<ResponseShortElementLibraryDto>> copyElements(
            @RequestBody @PathVariable(name = "id") @NotNull @Positive
            @Parameter(description = "Идентификатор типа оборудования, для копирования") Long id
            , @RequestBody @PathVariable(name = "copyId") @NotNull @Positive
            @Parameter(description = "Идентификатор исходного типа оборудования") Long copyId) {
        return ResponseEntity.ok().body(service.copyElements(id, copyId));
    }

    @Operation(summary = "Получить вид оборудования")
    @GetMapping("/equipment/{id}")
    public ResponseEntity<ResponseEquipmentLibraryDto> get(
            @PathVariable @NotNull @Positive @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить краткие сведения о видах оборудования")
    @GetMapping("/equipments")
    public ResponseEntity<List<ResponseShortEquipmentLibraryDto>> getAll(@RequestParam(required = false)
                                                                         @Parameter(description = "Наименование оборудования") String name) {
        return ResponseEntity.ok().body(service.getAll(name));
    }

    @Operation(summary = "Удаление вида оборудования")
    @DeleteMapping("/equipment/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Вид оборудования удален.");
    }
}