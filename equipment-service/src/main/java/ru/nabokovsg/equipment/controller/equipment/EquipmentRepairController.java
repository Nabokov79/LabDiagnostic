package ru.nabokovsg.equipment.controller.equipment;

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
import ru.nabokovsg.equipment.dto.equipmentRepair.NewEquipmentRepairDto;
import ru.nabokovsg.equipment.dto.equipmentRepair.ResponseEquipmentRepairDto;
import ru.nabokovsg.equipment.dto.equipmentRepair.UpdateEquipmentRepairDto;
import ru.nabokovsg.equipment.service.equipment.EquipmentRepairService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/equipment/repair",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные выполненного ремонта диагностируемого оборудования",
        description="API для работы с данными ремонта диагностируемого оборудования")
public class EquipmentRepairController {

    private final EquipmentRepairService service;

    @Operation(summary = "Добавить данные выполненного ремонта оборудования")
    @PostMapping
    public ResponseEntity<ResponseEquipmentRepairDto> save(@RequestBody @Valid
                                                           @Parameter(name = "Выполненный ремонт")
                                                           NewEquipmentRepairDto repairDto) {
        return ResponseEntity.ok().body(service.save(repairDto));
    }

    @Operation(summary = "Изменить данные выполненного ремонта оборудования")
    @PatchMapping
    public ResponseEntity<ResponseEquipmentRepairDto> update(@RequestBody @Valid
                                                             @Parameter(name = "Выполненный ремонт")
                                                             UpdateEquipmentRepairDto repairDto) {
        return ResponseEntity.ok().body(service.update(repairDto));
    }

    @Operation(summary = "Получить данные всех выполненных ремонтов оборудования")
    @GetMapping
    public ResponseEntity<List<ResponseEquipmentRepairDto>> getAll(
                                                    @RequestParam(name = "id") @NotNull @Positive
                                                    @Parameter(name = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAll(equipmentId));
    }

    @Operation(summary = "Удалить данные ремонта оборудования")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные ремонта успешно удалены.");
    }
}