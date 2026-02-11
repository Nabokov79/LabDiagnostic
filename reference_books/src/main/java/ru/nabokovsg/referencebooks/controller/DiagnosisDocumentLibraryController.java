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
import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.NewDiagnosisDocumentLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.ResponseDiagnosisDocumentLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.UpdateDiagnosisDocumentLibraryDto;
import ru.nabokovsg.referencebooks.service.DiagnosisDocumentLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Справочник видов документов по результатам диагностики",
        description="API для работы с справочником видов документов по результатам диагностики")
public class DiagnosisDocumentLibraryController {

    private final DiagnosisDocumentLibraryService service;

    @Operation(summary = "Добавить документ диагностики")
    @PostMapping("/diagnostic/document")
    public ResponseEntity<ResponseDiagnosisDocumentLibraryDto> save(
            @RequestBody @Valid
            @Parameter(name = "Документ диагностики") NewDiagnosisDocumentLibraryDto documentDto) {
        return ResponseEntity.ok().body(service.save(documentDto));
    }

    @Operation(summary = "Изменение документ диагностики")
    @PatchMapping("/diagnostic/document")
    public ResponseEntity<ResponseDiagnosisDocumentLibraryDto> update(
            @RequestBody @Valid
            @Parameter(name = "Документ диагностики") UpdateDiagnosisDocumentLibraryDto documentDto) {
        return ResponseEntity.ok().body(service.update(documentDto));
    }

    @Operation(summary = "Получить документ диагностики")
    @GetMapping("/diagnostic/document/{id}")
    public ResponseEntity<ResponseDiagnosisDocumentLibraryDto> get(@PathVariable @NotNull @Positive
                                                           @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все документы диагностики")
    @GetMapping("/diagnostic/documents")
    public ResponseEntity<List<ResponseDiagnosisDocumentLibraryDto>> getAll(
            @RequestParam(name = "name", required = false)
            @Parameter(description = "Наименование документа") String name) {
        return ResponseEntity.ok().body(service.getAll(name));
    }

    @Operation(summary = "Удалить документ диагностики")
    @DeleteMapping("/diagnostic/document/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Документ диагностики удален.");
    }
}