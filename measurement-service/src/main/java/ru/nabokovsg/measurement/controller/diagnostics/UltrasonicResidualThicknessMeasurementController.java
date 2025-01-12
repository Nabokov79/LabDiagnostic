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
import ru.nabokovsg.measurement.dto.ultrasonicResidualThicknessMeasurement.ResponseUltrasonicResidualThicknessMeasurementDto;
import ru.nabokovsg.measurement.dto.ultrasonicResidualThicknessMeasurement.UltrasonicResidualThicknessMeasurementDto;
import ru.nabokovsg.measurement.service.diagnostics.UltrasonicResidualThicknessMeasurementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/measurement/thickness/ultrasonic",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Ультразвуковая толщинометрия элементов, подэлементов оборудования",
        description="API для работы с данными ультразвукого измерения остаточной толщины элемента, подэлемента оборудования")
public class UltrasonicResidualThicknessMeasurementController {

    private final UltrasonicResidualThicknessMeasurementService service;

    @Operation(summary = "Добавить результат ультразвуковой толщинометрии")
    @PostMapping
    public ResponseEntity<ResponseUltrasonicResidualThicknessMeasurementDto> save(
                                                    @RequestBody @Valid @Parameter(name = "Данные измерения толщины")
                                                    UltrasonicResidualThicknessMeasurementDto measurementDto) {
        return ResponseEntity.ok().body(service.save(measurementDto));
    }

    @Operation(summary = "Получить результат измерения толщины")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUltrasonicResidualThicknessMeasurementDto> get(
            @PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные ультразвуковой толщинометрии")
    @GetMapping
    public ResponseEntity<List<ResponseUltrasonicResidualThicknessMeasurementDto>> getAll(
                                            @RequestParam(name = "equipmentId") @NotNull @Positive
                                            @Parameter(description = "Идентификатор оборудования") Long equipmentId,
                                            @RequestParam(name = "elementId", required = false)
                                            @Parameter(description = "Идентификатор элемента") Long elementId,
                                            @RequestParam(name = "partElementId", required = false)
                                            @Parameter(description = "Идентификатор подэлемента") Long partElementId) {
        return ResponseEntity.ok().body(service.getAll(equipmentId, elementId, partElementId));
    }

    @Operation(summary = "Удалить данные ультразвуковой толщинометрии")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные ультразвуковой толщинометрии успешно удалены.");
    }
}