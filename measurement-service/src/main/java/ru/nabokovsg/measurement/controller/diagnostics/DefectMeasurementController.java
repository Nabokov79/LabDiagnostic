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
import ru.nabokovsg.measurement.dto.defect.NewDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defect.ResponseDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.defect.UpdateDefectMeasurementDto;
import ru.nabokovsg.measurement.service.diagnostics.DefectMeasurementService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/measurement/defect",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Обнаруженные дефекты элементов, подэлементов оборудования",
        description="API для работы с данными измерений дефектов элементов, подэлементов оборудования")
public class DefectMeasurementController {

    private final DefectMeasurementService service;

    @Operation(summary = "Добавить дефект")
    @PostMapping
    public ResponseEntity<ResponseDefectMeasurementDto> save(
            @RequestBody @Valid @Parameter(name = "Дефект") NewDefectMeasurementDto defectDto) {
        return ResponseEntity.ok().body(service.save(defectDto));
    }

    @Operation(summary = "Изменить дефект")
    @PatchMapping
    public ResponseEntity<ResponseDefectMeasurementDto> update(
            @RequestBody @Valid @Parameter(name = "Дефект") UpdateDefectMeasurementDto defectDto) {
        return ResponseEntity.ok().body(service.update(defectDto));
    }

    @Operation(summary = "Получить дефект элемента оборудования")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDefectMeasurementDto> get(
            @PathVariable @NotNull @Positive @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить дефекты элементов(подэлементов) по идентификатору оборудования")
    @GetMapping
    public ResponseEntity<List<ResponseDefectMeasurementDto>> getAll(
                                            @RequestParam(name = "equipmentId") @NotNull @Positive
                                            @Parameter(description = "Идентификатор оборудования") Long equipmentId,
                                            @RequestParam(name = "elementId", required = false)
                                            @Parameter(description = "Идентификатор элемента") Long elementId,
                                            @RequestParam(name = "partElementId", required = false)
                                            @Parameter(description = "Идентификатор подэлемента") Long partElementId) {
        return ResponseEntity.ok().body(service.getAll(equipmentId, elementId, partElementId));
    }

    @Operation(summary = "Удалить дефект")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Измерения дефекта успешно удалены.");
    }
}