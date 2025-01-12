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
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.NewQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.ResponseQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.dto.qclDocumentType.UpdateQCLDocumentTypeDto;
import ru.nabokovsg.laboratoryqc.service.QCLDocumentTypeService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/laboratory/QCL/document/type",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Виды документов, оформляемые в лаборатории НК",
        description="API для работы с данными видов документов, оформляемых в лаборатории НК")
public class QCLDocumentTypeController {

    private final QCLDocumentTypeService service;

    @Operation(summary = "Добавление нового документа")
    @PostMapping
    public ResponseEntity<ResponseQCLDocumentTypeDto> save(
            @RequestBody @Valid @Parameter(description = "Вид документа") NewQCLDocumentTypeDto documentTypeDto) {
        return ResponseEntity.ok().body(service.save(documentTypeDto));
    }

    @Operation(summary = "Изменение информации в шаблоне ")
    @PatchMapping
    public ResponseEntity<ResponseQCLDocumentTypeDto> update(
            @RequestBody @Valid @Parameter(description = "Вид документа") UpdateQCLDocumentTypeDto documentTypeDto) {
        return ResponseEntity.ok().body(service.update(documentTypeDto));
    }

    @Operation(summary = "Получить вид документа")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseQCLDocumentTypeDto> get(@PathVariable @NotNull
                                                              @Positive @Parameter(name = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все виды документов")
    @GetMapping("/all")
    public ResponseEntity<List<ResponseQCLDocumentTypeDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удалить вид документа")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Вид документа успешно удален.");
    }
}