package ru.nabokovsg.equipment.controller.library;

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
import ru.nabokovsg.equipment.dto.equipmentLibrary.NewEquipmentLibraryDto;
import ru.nabokovsg.equipment.dto.equipmentLibrary.ResponseEquipmentLibraryDto;
import ru.nabokovsg.equipment.dto.equipmentLibrary.UpdateEquipmentLibraryDto;
import ru.nabokovsg.equipment.model.common.Copy;
import ru.nabokovsg.equipment.service.library.EquipmentLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/equipment/library/equipment",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name = "Библиотека типа оборудования",
        description = "API для работы с данными библиотеки типа оборудования")
public class EquipmentLibraryController {

    private final EquipmentLibraryService service;

    @Operation(summary = "Добавление нового вид оборудования")
    @PostMapping
    public ResponseEntity<ResponseEquipmentLibraryDto> save(
            @RequestBody @Valid @Parameter(description = "Вид оборудования") NewEquipmentLibraryDto equipmentDto) {
        return ResponseEntity.ok().body(service.save(equipmentDto));
    }

    @Operation(summary = "Изменение данных вида оборудования")
    @PatchMapping
    public ResponseEntity<ResponseEquipmentLibraryDto> update(
            @RequestBody @Valid @Parameter(description = "Вида оборудования") UpdateEquipmentLibraryDto equipmentDto) {
        return ResponseEntity.ok().body(service.update(equipmentDto));
    }

    @Operation(summary = "Добавить вид оборудования копированием сохраненного")
    @PostMapping("/copy")
    public ResponseEntity<ResponseEquipmentLibraryDto> copy(
            @RequestBody @Validated({Copy.class}) @Parameter(description = "Вида оборудования") NewEquipmentLibraryDto equipmentDto) {
        return ResponseEntity.ok().body(service.copy(equipmentDto));
    }

    @Operation(summary = "Получить вид оборудования")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEquipmentLibraryDto> get(
            @PathVariable @NotNull @Positive @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить краткие сведения о видах оборудования")
    @GetMapping
    public ResponseEntity<List<ResponseEquipmentLibraryDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление вида оборудования")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                             @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Вид оборудования успешно удален.");
    }
}