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
import ru.nabokovsg.measurement.dto.geodesicMeasurement.NewGeodesicMeasurementDto;
import ru.nabokovsg.measurement.dto.geodesicMeasurement.ResponseGeodesicMeasurementDto;
import ru.nabokovsg.measurement.dto.geodesicMeasurement.UpdateGeodesicMeasurementDto;
import ru.nabokovsg.measurement.service.diagnostics.GeodesicMeasurementsService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/measurement/geodesy",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Измерения геодезической съемки(нивелировании) оборудования",
        description="API для работы с данными измерений геодезической съемки(нивелировании) оборудования")
public class GeodesicMeasurementsController {

    private final GeodesicMeasurementsService service;

    @Operation(summary = "Добавить данные геодезический съемки оборудования")
    @PostMapping
    public ResponseEntity<List<ResponseGeodesicMeasurementDto>> save(
            @RequestBody @Valid
            @Parameter(name = "Данные измерений геодезической съемки") NewGeodesicMeasurementDto measurementDto) {
        return ResponseEntity.ok().body(service.save(measurementDto));
    }

    @Operation(summary = "Добавить данные геодезический съемки оборудования")
    @PatchMapping
    public ResponseEntity<List<ResponseGeodesicMeasurementDto>> update(
            @RequestBody @Valid
            @Parameter(name = "Данные измерений геодезической съемки") UpdateGeodesicMeasurementDto measurementDto) {
        return ResponseEntity.ok().body(service.update(measurementDto));
    }

    @Operation(summary = "Получить результат геодезического измерения")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseGeodesicMeasurementDto> get(@PathVariable @NotNull @Positive
                                                                   @Parameter(name = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные геодезических измерений по оборудования")
    @GetMapping("/{equipmentId}")
    public ResponseEntity<List<ResponseGeodesicMeasurementDto>> getAll(
                                            @PathVariable(name = "equipmentId") @NotNull @Positive
                                            @Parameter(description = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAll(equipmentId));
    }
}