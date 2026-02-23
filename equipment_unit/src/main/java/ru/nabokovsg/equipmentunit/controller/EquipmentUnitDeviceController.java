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
import ru.nabokovsg.equipmentunit.dto.equipment.NewEquipmentUnitDto;
import ru.nabokovsg.equipmentunit.dto.equipment.ResponseEquipmentUnitDto;
import ru.nabokovsg.equipmentunit.dto.equipment.UpdateEquipmentUnitDto;
import ru.nabokovsg.equipmentunit.dto.equipment.ResponseEquipmentUnitDeviceDto;
import ru.nabokovsg.equipmentunit.model.Device;
import ru.nabokovsg.equipmentunit.service.EquipmentUnitService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/equipment",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Единица оборудования тепловой сети",
        description="API для работы с данными оборудования технического устройства участка тепловой сети")
public class EquipmentUnitDeviceController {

    private final EquipmentUnitService service;

    @Operation(summary = "Добавление единицу оборудования технического устройства")
    @PostMapping("/device")
    public ResponseEntity<ResponseEquipmentUnitDto> save(@RequestBody @Validated({Device.class})
                                                     @Parameter(description = "Единица оборудование")
                                                     NewEquipmentUnitDto equipmentDto) {
        return ResponseEntity.ok().body(service.save(equipmentDto));
    }

    @Operation(summary = "Изменить данные единицы оборудования технического устройства")
    @PatchMapping("/device")
    public ResponseEntity<ResponseEquipmentUnitDto> update(@RequestBody @Validated({Device.class})
                                                     @Parameter(description = "Единица оборудование")
                                                     UpdateEquipmentUnitDto equipmentDto) {
        return ResponseEntity.ok().body(service.update(equipmentDto));
    }

    @Operation(summary = "Получить единицу оборудования технического устройства")
    @GetMapping("/device/{id}")
    public ResponseEntity<ResponseEquipmentUnitDto> get(@PathVariable @NotNull @Positive
                                                    @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все оборудование тепловых сетей")
    @GetMapping("/devices")
    public ResponseEntity<List<ResponseEquipmentUnitDeviceDto>> getAll(@RequestParam(name = "search", required = false)
                                                                    @Parameter(description = "поиск") String search) {
        return ResponseEntity.ok().body(service.getAllEquipmentUnitDevice(search));
    }

    @Operation(summary = "Удалить единицу оборудования технического устройства")
    @DeleteMapping("/device/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Оборудование технического устройства удалено.");
    }
}