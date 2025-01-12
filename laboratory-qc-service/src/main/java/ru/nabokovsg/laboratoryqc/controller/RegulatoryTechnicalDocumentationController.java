package ru.nabokovsg.laboratoryqc.controller;

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
import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.NewRegulatoryTechnicalDocumentationDto;
import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.ResponseRegulatoryTechnicalDocumentationDto;
import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.UpdateRegulatoryTechnicalDocumentationDto;
import ru.nabokovsg.laboratoryqc.service.RegulatoryTechnicalDocumentationService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/laboratory/QCL/document/regulatory/technical",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Виды документов, оформляемые в лаборатории НК",
        description="API для работы с данными видов документов, оформляемых в лаборатории НК")
public class RegulatoryTechnicalDocumentationController {

    private final RegulatoryTechnicalDocumentationService service;

    @Operation(summary = "Добавление данных документа")
    @PostMapping
    public ResponseEntity<ResponseRegulatoryTechnicalDocumentationDto> save(@RequestBody @Valid
                        @Parameter(description = "Документ") NewRegulatoryTechnicalDocumentationDto documentationDto) {
        return ResponseEntity.ok().body(service.save(documentationDto));
    }

    @Operation(summary = "Изменение данных документа")
    @PatchMapping
    public ResponseEntity<ResponseRegulatoryTechnicalDocumentationDto> update(@RequestBody @Valid
                    @Parameter(description = "Документ") UpdateRegulatoryTechnicalDocumentationDto documentationDto) {
        return ResponseEntity.ok().body(service.update(documentationDto));
    }

    @Operation(summary = "Получить документ для добавления в шаблон документа")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseRegulatoryTechnicalDocumentationDto> get(@PathVariable @NotNull @Positive
                                                                 @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных документов")
    @GetMapping
    public ResponseEntity<List<ResponseRegulatoryTechnicalDocumentationDto>> getAll(
            @RequestParam(name = "number", required = false)
            @Parameter(description = "Номер документа, Название документа") String text) {
        return ResponseEntity.ok().body(service.getAll(text));
    }

    @Operation(summary = "Удаление данных документа")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Документ успешно удален.");
    }
}