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
import ru.nabokovsg.referencebooks.dto.elementLibrary.NewElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.UpdateElementLibraryDto;
import ru.nabokovsg.referencebooks.model.CopyElement;
import ru.nabokovsg.referencebooks.model.NewElement;
import ru.nabokovsg.referencebooks.service.ElementLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Справочник элементов оборудования",
        description="API для работы с справочником видов элементов оборудования")
public class ElementLibraryController {

    private final ElementLibraryService service;

    @Operation(summary = "Добавление нового элемента")
    @PostMapping("/element")
    public ResponseEntity<ResponseElementLibraryDto> save(@RequestBody @Validated
                                                          @Parameter(description = "Элемент")
                                                          NewElementLibraryDto elementDto) {
        return ResponseEntity.ok().body(service.save(elementDto));
    }

    @Operation(summary = "Изменение данных элемента")
    @PatchMapping("/element")
    public ResponseEntity<ResponseElementLibraryDto> update(@RequestBody @Valid
                                                            @Parameter(description = "Элемент")
                                                            UpdateElementLibraryDto elementDto) {
        return ResponseEntity.ok().body(service.update(elementDto));
    }

    @Operation(summary = "Получить элемент оборудования")
    @GetMapping("/element/{id}")
    public ResponseEntity<ResponseElementLibraryDto> get(@PathVariable(name = "id") @NotNull @Positive
                                                         @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все элементы оборудования")
    @GetMapping("/elements/{id}")
    public ResponseEntity<List<ResponseElementLibraryDto>> getAll(
            @PathVariable(name = "id") @NotNull @Positive
            @Parameter(description = "Идентификатор типа оборудования") Long equipmentLibraryId) {
        return ResponseEntity.ok().body(service.getAll(equipmentLibraryId));
    }

    @Operation(summary = "Копировать элементы")
    @PostMapping("/element/copy/{id}")
    public ResponseEntity<List<ResponseElementLibraryDto>> copy(@RequestBody @Validated({CopyElement.class})
                                                                    @PathVariable(name = "id") @NotNull @Positive
                                                                    @Parameter(description = "Идентификатор типа оборудования") Long equipmentLibraryId) {
        return ResponseEntity.ok().body(service.copy(equipmentLibraryId));
    }

    @Operation(summary = "Удаление элемента оборудования")
    @DeleteMapping("/element/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Тип элемента оборудования удален.");
    }
}