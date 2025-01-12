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
import ru.nabokovsg.equipment.dto.equipmentPassport.NewEquipmentPassportDto;
import ru.nabokovsg.equipment.dto.equipmentPassport.ResponseEquipmentPassportDto;
import ru.nabokovsg.equipment.dto.equipmentPassport.UpdateEquipmentPassportDto;
import ru.nabokovsg.equipment.service.equipment.EquipmentPassportService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/equipment/passport",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Паспорт оборудования",
        description="API для работы с данными паспорта оборудования")
public class EquipmentPassportController {

    private final EquipmentPassportService service;

    @Operation(summary = "Добавление данных паспорта")
    @PostMapping
    public ResponseEntity<ResponseEquipmentPassportDto> save(@RequestBody @Valid
                                               @Parameter(description = "Паспорт") NewEquipmentPassportDto passportDto) {
        return ResponseEntity.ok().body(service.save(passportDto));
    }

    @Operation(summary = "Изменение данных паспорта")
    @PatchMapping
    public ResponseEntity<ResponseEquipmentPassportDto> update(@RequestBody @Valid
                                                 @Parameter(description = "Паспорт") UpdateEquipmentPassportDto passportDto) {
        return ResponseEntity.ok().body(service.update(passportDto));
    }

    @Operation(summary = "Получить данные паспорта оборудования")
    @GetMapping
    public ResponseEntity<List<ResponseEquipmentPassportDto>> getAll(
                                       @RequestParam(name = "id") @NotNull @Positive
                                       @Parameter(description = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAll(equipmentId));
    }

    @Operation(summary = "Удаление данных паспорта")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные паспорта успешно удалены.");
    }
}