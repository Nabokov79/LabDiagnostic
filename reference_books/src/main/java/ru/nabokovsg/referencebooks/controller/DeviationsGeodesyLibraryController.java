package ru.nabokovsg.referencebooks.controller;

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
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.NewDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.ResponseDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.UpdateDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.service.DeviationsGeodesyLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные допустимых значений отклонений геодезических измерений",
        description="API для работы с данными допустимых значений отклонений геодезических измерений")
public class DeviationsGeodesyLibraryController {

    private final DeviationsGeodesyLibraryService service;

    @Operation(summary = "Добавить допустимое значение отклонения")
    @PostMapping("/geodesy/deviation")
    public ResponseEntity<ResponseDeviationsGeodesyLibraryDto> save(
            @RequestBody @Valid
            @Parameter(name = "Допустимое значение отклонения") NewDeviationsGeodesyLibraryDto geodesyDto) {
        return ResponseEntity.ok().body(service.save(geodesyDto));
    }

    @Operation(summary = "Измененить допустимое значение отклонения")
    @PatchMapping("/geodesy/deviation")
    public ResponseEntity<ResponseDeviationsGeodesyLibraryDto> update(
                                                @RequestBody @Valid @Parameter(name = "Допустимое значение отклонения")
                                                UpdateDeviationsGeodesyLibraryDto geodesyDto) {
        return ResponseEntity.ok().body(service.update(geodesyDto));
    }

    @Operation(summary = "Получить допустимое измерение")
    @GetMapping("/geodesy/deviation/{id}")
    public ResponseEntity<ResponseDeviationsGeodesyLibraryDto> get(@PathVariable @NotNull @Positive
                                                                    @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все допустимые значения отклонений")
    @GetMapping("/geodesy/deviations/{id}")
    public ResponseEntity<List<ResponseDeviationsGeodesyLibraryDto>> getAll(
            @PathVariable(name = "id") @NotNull @Positive
            @Parameter(description = "Идентификатор типа оборудования") Long equipmentLibraryId) {
        return ResponseEntity.ok().body(service.getAll(equipmentLibraryId));
    }

    @Operation(summary = "Удалить допустимое значение отклонения")
    @DeleteMapping("/geodesy/deviation/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Допустимое значение отклонения удалено.");
    }
}