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
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.NewRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.ResponseRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.ResponseShortRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.UpdateRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.service.RegulatoryDocumentationLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Справочник нормативно-технической документации",
        description="API для работы с справочником нормативно-технической документации")
public class RegulatoryDocumentationLibraryController {

    private final RegulatoryDocumentationLibraryService service;

    @Operation(summary = "Добавление данных документа")
    @PostMapping("/documentation")
    public ResponseEntity<ResponseRegulatoryDocumentationLibraryDto> save(@RequestBody @Valid
                                                                          @Parameter(description = "Документ")
                                                                          NewRegulatoryDocumentationLibraryDto documentationDto) {
        return ResponseEntity.ok().body(service.save(documentationDto));
    }

    @Operation(summary = "Изменение данных документа")
    @PatchMapping("/documentation")
    public ResponseEntity<ResponseRegulatoryDocumentationLibraryDto> update(@RequestBody @Valid
                                                                            @Parameter(description = "Документ")
                                                                            UpdateRegulatoryDocumentationLibraryDto documentationDto) {
        return ResponseEntity.ok().body(service.update(documentationDto));
    }

    @Operation(summary = "Получить документ")
    @GetMapping("/documentation/{id}")
    public ResponseEntity<ResponseRegulatoryDocumentationLibraryDto> get(@PathVariable @NotNull @Positive
                                                                         @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все документы")
    @GetMapping("/documentations")
    public ResponseEntity<List<ResponseShortRegulatoryDocumentationLibraryDto>> getAll(
            @RequestParam(name = "text", required = false)
            @Parameter(description = "Тип, номер, наименование документа") String text) {
        return ResponseEntity.ok().body(service.getAll(text));
    }

    @Operation(summary = "Удаление документ")
    @DeleteMapping("/documentation/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Документ удален.");
    }
}