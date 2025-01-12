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
import ru.nabokovsg.measurement.dto.hardnessMeasurement.NewHardnessMeasurementDto;
import ru.nabokovsg.measurement.dto.hardnessMeasurement.ResponseElementHardnessMeasurementDto;
import ru.nabokovsg.measurement.dto.hardnessMeasurement.UpdateHardnessMeasurementDto;
import ru.nabokovsg.measurement.service.diagnostics.HardnessMeasurementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/measurement/hardness",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные измерений твердости металла оборудования",
        description="API для работы с данными измерений твердости металла оборудования")
public class HardnessMeasurementController {

    private final HardnessMeasurementService service;

    @Operation(summary = "Добавить данные измерений твердости металла элементов, подэлементов оборудования")
    @PostMapping
    public ResponseEntity<ResponseElementHardnessMeasurementDto> save(@RequestBody @Valid
                                                                 @Parameter(name = "Данные измерений твердости")
                                                                      NewHardnessMeasurementDto measurementDto) {
        return ResponseEntity.ok().body(service.save(measurementDto));
    }

    @Operation(summary = "Изменить данные измерений твердости металла элементов, подэлементов оборудования")
    @PatchMapping
    public ResponseEntity<ResponseElementHardnessMeasurementDto> update(@RequestBody @Valid
                                                                      @Parameter(name = "Данные измерений твердости")
                                                                      UpdateHardnessMeasurementDto measurementDto) {
        return ResponseEntity.ok().body(service.update(measurementDto));
    }

    @Operation(summary = "Получить данные данные измерений твердости металла элемента, подэлемента оборудования")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseElementHardnessMeasurementDto> get(
            @PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные данные измерений твердости металла элементов, подэлементов оборудования")
    @GetMapping
    public ResponseEntity<List<ResponseElementHardnessMeasurementDto>> getAll(
                                            @RequestParam(name = "equipmentId") @NotNull @Positive
                                            @Parameter(description = "Идентификатор оборудования") Long equipmentId,
                                            @RequestParam(name = "elementId", required = false)
                                            @Parameter(description = "Идентификатор элемента") Long elementId,
                                            @RequestParam(name = "partElementId", required = false)
                                            @Parameter(description = "Идентификатор подэлемента") Long partElementId) {
        return ResponseEntity.ok().body(service.getAll(equipmentId, elementId, partElementId));
    }

    @Operation(summary = "Удалить результат измерения твердости")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Результат измерения твердости успешно удален.");
    }
}