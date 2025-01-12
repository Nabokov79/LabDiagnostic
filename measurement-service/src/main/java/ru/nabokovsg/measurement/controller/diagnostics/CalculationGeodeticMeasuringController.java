package ru.nabokovsg.measurement.controller.diagnostics;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculatingOppositePointDto;
import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculationControlPointDto;
import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculationNeighboringPointDto;
import ru.nabokovsg.measurement.dto.calculationGeodesicMeasurements.ResponseCalculationReferencePointDto;
import ru.nabokovsg.measurement.service.calculationGeodesicMeasurements.CalculationGeodeticMeasuringService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/measurement/calculation/geodesy",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Результаты расчета геодезической съемки(нивелировании) оборудования",
        description="API для работы с данными результатов расчета геодезической съемки(нивелировании) оборудования")
public class CalculationGeodeticMeasuringController {

    private final CalculationGeodeticMeasuringService service;

    @Operation(summary = "Получить результаты расчета измерений по реперам")
    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseCalculationReferencePointDto>> getAllReferencePoint(
                                                    @PathVariable(name = "id") @NotNull @Positive
                                                    @Parameter(name = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAllReferencePoint(equipmentId));
    }

    @Operation(summary = "Получить результаты расчета измерений по контрольным точкам")
    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseCalculationControlPointDto>> getAllControlPoint(
                                                    @PathVariable(name = "id") @NotNull @Positive
                                                    @Parameter(name = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAllControlPoint(equipmentId));
    }

    @Operation(summary = "Получить результаты расчета измерений диаметральных точек")
    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseCalculatingOppositePointDto>> getAllOppositePoint(
                                                    @PathVariable(name = "id") @NotNull @Positive
                                                    @Parameter(name = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAllOppositePoint(equipmentId));
    }

    @Operation(summary = "Получить результаты расчета измерений соседних точек")
    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseCalculationNeighboringPointDto>> getAllNeighboringPoint(
                                                @PathVariable(name = "id") @NotNull @Positive
                                                @Parameter(name = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAllNeighboringPoint(equipmentId));
    }
}