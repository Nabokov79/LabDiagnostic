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
import ru.nabokovsg.referencebooks.dto.partElementLibrary.NewPartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.ResponsePartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.ResponseShortPartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.UpdatePartElementLibraryDto;
import ru.nabokovsg.referencebooks.service.PartElementLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Справочник подэлементов элементов оборудования",
        description="API для работы с справочником видов подэлементов элементов оборудования")
public class PartElementLibraryController {

    private final PartElementLibraryService service;

    @Operation(summary = "Добавление нового подэлемента")
    @PostMapping("/part")
    public ResponseEntity<ResponseShortPartElementLibraryDto> save(
            @RequestBody @Valid
            @Parameter(description = "Подэлемент") NewPartElementLibraryDto partElementDto) {
        return ResponseEntity.ok().body(service.save(partElementDto));
    }

    @Operation(summary = "Изменение данных подэлемента")
    @PatchMapping("/part")
    public ResponseEntity<ResponseShortPartElementLibraryDto> update(
            @RequestBody @Valid
            @Parameter(description = "Подэлемент") UpdatePartElementLibraryDto partElementDto) {
        return ResponseEntity.ok().body(service.update(partElementDto));
    }

    @Operation(summary = "Получить подэлемент элемента")
    @GetMapping("/part/{id}")
    public ResponseEntity<ResponsePartElementLibraryDto> get(@PathVariable(name = "id") @NotNull @Positive
                                                             @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все подэлементы элемента")
    @GetMapping("/parts/{id}")
    public ResponseEntity<List<ResponseShortPartElementLibraryDto>> getAll(
            @PathVariable(name = "id") @NotNull @Positive
            @Parameter(description = "Идентификатор типа элемента") Long id
            , @RequestParam(name = "name", required = false)
            @Parameter(description = "Наименование подэлемента") String name) {
        return ResponseEntity.ok().body(service.getAll(id, name));
    }

    @Operation(summary = "Удаление подэлемента элемента оборудования")
    @DeleteMapping("/part/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Подэлемент оборудования удален.");
    }
}