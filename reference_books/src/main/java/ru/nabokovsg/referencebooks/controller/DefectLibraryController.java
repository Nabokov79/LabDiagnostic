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
import ru.nabokovsg.referencebooks.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.ResponseShortDefectLibraryDto;
import ru.nabokovsg.referencebooks.dto.defectLibrary.UpdateDefectLibraryDto;
import ru.nabokovsg.referencebooks.service.DefectLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Справочник дефектов", description="API для работы с справочником дефектов основного металла и сварных швов")
public class DefectLibraryController {

    private final DefectLibraryService service;

    @Operation(summary = "Добавление новых дефектов оборудования")
    @PostMapping("/defect")
    public ResponseEntity<ResponseShortDefectLibraryDto> save(
            @RequestBody @Valid @Parameter(description = "Дефект") NewDefectLibraryDto defectDto) {
        return ResponseEntity.ok().body(service.save(defectDto));
    }

    @Operation(summary = "Изменение данных дефектов оборудования")
    @PatchMapping("/defect")
    public ResponseEntity<ResponseShortDefectLibraryDto> update(
            @RequestBody @Valid @Parameter(description = "Дефект") UpdateDefectLibraryDto defectDto) {
        return ResponseEntity.ok().body(service.update(defectDto));
    }

    @Operation(summary = "Получить дефект")
    @GetMapping("/defect/{id}")
    public ResponseEntity<ResponseDefectLibraryDto> get(@PathVariable @NotNull @Positive
                                                        @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить дефекты")
    @GetMapping("/defects")
    public ResponseEntity<List<ResponseShortDefectLibraryDto>> getAll(
                                          @RequestParam(name = "search", required = false) String search) {
        return ResponseEntity.ok().body(service.getAll(search));
    }

    @Operation(summary = "Удалить дефект")
    @DeleteMapping("/defect/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Дефект удален.");
    }
}