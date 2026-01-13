package ru.nabokovsg.referencebooks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.referencebooks.dto.measurementParameterLibrary.ResponseMeasurementParameterLibraryDto;
import ru.nabokovsg.referencebooks.service.MeasuredParameterLibraryService;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Справочник измеряемых параметров дефектов, ремонтов",
        description="API для работы с справочником измеряемых параметров дефектов, ремонтов")
public class MeasurementParameterLibraryController {

    private final MeasuredParameterLibraryService service;

    @Operation(summary = "Получить дефект")
    @GetMapping("/parameter/{id}")
    public ResponseEntity<ResponseMeasurementParameterLibraryDto> get(@PathVariable @NotNull @Positive
                                                        @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Удалить измеряемый параметр")
    @DeleteMapping("/parameter/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Измеряемый параметр удален.");
    }
}