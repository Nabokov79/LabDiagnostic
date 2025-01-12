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
import ru.nabokovsg.equipment.dto.equipmentInspection.NewEquipmentInspectionDto;
import ru.nabokovsg.equipment.dto.equipmentInspection.ResponseEquipmentInspectionDto;
import ru.nabokovsg.equipment.dto.equipmentInspection.UpdateEquipmentInspectionDto;
import ru.nabokovsg.equipment.service.equipment.EquipmentInspectionService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/equipment/inspection",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные выполненных обследований оборудования",
        description="API для работы с данными обследований оборудования")
public class EquipmentInspectionController {

    private final EquipmentInspectionService service;

    @Operation(summary = "Добавить данные обследования оборудования")
    @PostMapping
    public ResponseEntity<ResponseEquipmentInspectionDto> save(@RequestBody @Valid
                                                               @Parameter(name = "Обследование оборудования")
                                                               NewEquipmentInspectionDto inspectionDto) {
        return ResponseEntity.ok().body(service.save(inspectionDto));
    }

    @Operation(summary = "Изменить данные обследования оборудования")
    @PatchMapping
    public ResponseEntity<ResponseEquipmentInspectionDto> update(@RequestBody @Valid
                                                                 @Parameter(name = "Обследование оборудования")
                                                                 UpdateEquipmentInspectionDto inspectionDto) {
        return ResponseEntity.ok().body(service.update(inspectionDto));
    }

    @Operation(summary = "Получить данные всех обследований оборудования")
    @GetMapping
    public ResponseEntity<List<ResponseEquipmentInspectionDto>> getAll(
                                            @RequestParam(name = "id") @NotNull @Positive
                                            @Parameter(description = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAll(equipmentId));
    }

    @Operation(summary = "Удалить измеренный обследования")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные обследования успешно удалены.");
    }
}