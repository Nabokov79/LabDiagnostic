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
import ru.nabokovsg.measurement.dto.calculationMeasurement.ResponseCalculationDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.calculationMeasurement.ResponseCalculationRepairMeasurementDto;
import ru.nabokovsg.measurement.service.diagnostics.CalculationMeasurementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/measurement/calculation",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Рассчитанные результаты измерения дефектов и мест ремонта элементов оборудования",
        description="API для работы с рассчитанные результатами измерения дефектов и мест ремонта элементов оборудования")
public class CalculationMeasurementController {

    private final CalculationMeasurementService service;

    @Operation(summary = "Получить рассчитанные результаты измерений по дефектов")
    @GetMapping("/defect/{id}")
    public ResponseEntity<List<ResponseCalculationDefectMeasurementDto>> getAllDefect(
            @PathVariable(name = "id") @NotNull @Positive
            @Parameter(name = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAllDefect(equipmentId));
    }

    @Operation(summary = "Получить рассчитанные результаты измерений мест ремонтов")
    @GetMapping("/repair/{id}")
    public ResponseEntity<List<ResponseCalculationRepairMeasurementDto>> getAllRepair(
            @PathVariable(name = "id") @NotNull @Positive
            @Parameter(name = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAllRepair(equipmentId));
    }
}
