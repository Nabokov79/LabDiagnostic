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
import ru.nabokovsg.referencebooks.validators.DefectValidator;

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
    private final DefectValidator validator;

    @Operation(summary = "Добавление новых дефектов оборудования")
    @PostMapping("/defect")
    public ResponseEntity<ResponseDefectLibraryDto> save(
            @RequestBody @Valid @Parameter(description = "Дефект") NewDefectLibraryDto defectDto) {
        validator.validNew(defectDto);
        return ResponseEntity.ok().body(service.save(defectDto));
    }

    @Operation(summary = "Изменение данных дефектов оборудования")
    @PatchMapping("/defect")
    public ResponseEntity<ResponseDefectLibraryDto> update(
            @RequestBody @Valid @Parameter(description = "Дефект") UpdateDefectLibraryDto defectDto) {
        validator.validUpdate(defectDto);
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
                                                        @RequestParam(name = "name", required = false) String name,
                                        @RequestParam(name = "documentation", required = false) String documentation) {
        return ResponseEntity.ok().body(service.getAll(name, documentation));
    }

    @Operation(summary = "Удалить дефект")
    @DeleteMapping("/defect/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные дефекта успешно удалены.");
    }
}