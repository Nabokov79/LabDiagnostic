package ru.nabokovsg.measurement.controller.diagnostics;

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
import ru.nabokovsg.measurement.dto.repairMeasurement.NewRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.ResponseRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.ResponseShortRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.UpdateRepairMeasurementDto;
import ru.nabokovsg.measurement.service.diagnostics.RepairMeasurementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/measurement/repair",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Выполненные ремонты элементов, подэлементов оборудования",
        description="API для работы с данными выполненных ремонтов элементов, подэлементов оборудования")
public class RepairMeasurementController {

    private final RepairMeasurementService service;

    @Operation(summary = "Добавить выполненный ремонт элемента")
    @PostMapping
    public ResponseEntity<ResponseShortRepairMeasurementDto> save(
                            @RequestBody @Valid
                            @Parameter(description = "Выполненный ремонт") NewRepairMeasurementDto repairDto) {
        return ResponseEntity.ok().body(service.save(repairDto));
    }

    @Operation(summary = "Изменить выполненный ремонт элемента")
    @PatchMapping
    public ResponseEntity<ResponseShortRepairMeasurementDto> update(
            @RequestBody @Valid
            @Parameter(description = "Выполненный ремонт") UpdateRepairMeasurementDto repairDto) {
        return ResponseEntity.ok().body(service.update(repairDto));
    }

    @Operation(summary = "Получить выполненный ремонт элемента")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseRepairMeasurementDto> get(
            @PathVariable @NotNull @Positive @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить выполненные ремонты элементов оборудования по идентификатору оборудования")
    @GetMapping
    public ResponseEntity<List<ResponseShortRepairMeasurementDto>> getAll(
                                            @RequestParam(name = "equipmentId") @NotNull @Positive
                                            @Parameter(description = "Идентификатор оборудования") Long equipmentId,
                                            @RequestParam(name = "elementId", required = false)
                                            @Parameter(description = "Идентификатор элемента") Long elementId,
                                            @RequestParam(name = "partElementId", required = false)
                                            @Parameter(description = "Идентификатор подэлемента") Long partElementId) {
        return ResponseEntity.ok().body(service.getAll(equipmentId, elementId, partElementId));
    }

    @Operation(summary = "Удалить ремонт элемента")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Выполненный ремонт элемента оборудования успешно удален.");
    }
}