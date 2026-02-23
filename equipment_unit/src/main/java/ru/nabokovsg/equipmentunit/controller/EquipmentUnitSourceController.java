package ru.nabokovsg.equipmentunit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.equipmentunit.dto.equipment.*;
import ru.nabokovsg.equipmentunit.service.EquipmentUnitService;
import ru.nabokovsg.equipmentunit.model.Source;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/equipment",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Единица оборудования источника теплоснабжения",
        description="API для работы с оборудования источников теплоснабжения")
public class EquipmentUnitSourceController {

    private final EquipmentUnitService service;

    @Operation(summary = "Добавить единицу оборудования")
    @PostMapping
    public ResponseEntity<ResponseEquipmentUnitDto> save(@RequestBody @Validated({Source.class})
                                                     @Parameter(description = "Единица оборудование")
                                                     NewEquipmentUnitDto equipmentDto) {
        return ResponseEntity.ok().body(service.save(equipmentDto));
    }
    @Operation(summary = "Изменить данные единицы оборудования")
    @PatchMapping
    public ResponseEntity<ResponseEquipmentUnitDto> update(@RequestBody @Validated({Source.class})
                                                       @Parameter(description = "Единица оборудование")
                                                       UpdateEquipmentUnitDto equipmentDto) {
        return ResponseEntity.ok().body(service.update(equipmentDto));
    }

    @Operation(summary = "Получить единицу оборудования источника теплоснабжения")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEquipmentUnitDto> get(@PathVariable @NotNull @Positive
                                                    @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все оборудование источников теплоснабжения")
    @GetMapping("/sources")
    public ResponseEntity<List<ResponseEquipmentUnitSourceDto>> getAllEquipmentUnitSource(@RequestParam(name = "search", required = false)
                                                                     @Parameter(description = "поиск") String search) {
        return ResponseEntity.ok().body(service.getAllEquipmentUnitSource(search));
    }

    @Operation(summary = "Получить все оборудование тепловых сетей")
    @GetMapping("/devices")
    public ResponseEntity<List<ResponseEquipmentUnitDeviceDto>> getAllEquipmentUnitDevice(@RequestParam(name = "search", required = false)
                                                                       @Parameter(description = "поиск") String search) {
        return ResponseEntity.ok().body(service.getAllEquipmentUnitDevice(search));
    }

    @Operation(summary = "Удалить единицу оборудования источника теплоснабжения")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Оборудование источника теплоснабжения удалено.");
    }
}