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
import ru.nabokovsg.equipment.dto.equipment.NewEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.ResponseEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.ResponseShortEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.UpdateEquipmentDto;
import ru.nabokovsg.equipment.service.equipment.EquipmentService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/equipment",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Оборудование",
        description="API для работы с данными оборудования котельных, ЦТП")
public class EquipmentController {

    private final EquipmentService service;

    @Operation(summary = "Добавление данных оборудования")
    @PostMapping
    public ResponseEntity<ResponseEquipmentDto> save(@RequestBody @Valid @Parameter(description = "Оборудование")
                                                               NewEquipmentDto equipmentDto) {
        return ResponseEntity.ok().body(service.save(equipmentDto));
    }

    @Operation(summary = "Изменение данных оборудования")
    @PatchMapping
    public ResponseEntity<ResponseEquipmentDto> update(@RequestBody @Valid @Parameter(description = "Оборудование")
                                                       UpdateEquipmentDto equipmentDto) {
        return ResponseEntity.ok().body(service.update(equipmentDto));
    }

    @Operation(summary = "Получить оборудование")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEquipmentDto> get(@PathVariable @NotNull @Positive
                                                                 @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все оборудование котельной, ЦТП")
    @GetMapping
    public ResponseEntity<List<ResponseShortEquipmentDto>> getAll(
                                            @RequestParam(name = "id") @NotNull @Positive
                                            @Parameter(description = "Идентификатор котельной, ЦТП") Long buildingId) {
        return ResponseEntity.ok().body(service.getAll(buildingId));
    }

    @Operation(summary = "Удаление оборудования")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                             @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Оборудование успешно удалено.");
    }
}