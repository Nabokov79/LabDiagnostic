package ru.nabokovsg.equipment.controller.library;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.equipment.dto.partElementLibrary.NewPartElementLibraryDto;
import ru.nabokovsg.equipment.dto.partElementLibrary.ResponsePartElementLibraryDto;
import ru.nabokovsg.equipment.dto.partElementLibrary.UpdatePartElementLibraryDto;
import ru.nabokovsg.equipment.service.library.PartElementLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/equipment/library/element/part",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Библиотека подэлементов",
        description="API для работы с данными библиотеки подэлементов элементов")
public class PartElementLibraryController {

    private final PartElementLibraryService service;

    @Operation(summary = "Добавление нового подэлемента")
    @PostMapping
    public ResponseEntity<ResponsePartElementLibraryDto> save(
                                @RequestBody @Valid
                                @Parameter(description = "Подэлемент") NewPartElementLibraryDto partElementDto) {
        return ResponseEntity.ok().body(service.save(partElementDto));
    }

    @Operation(summary = "Изменение данных подэлемента")
    @PatchMapping
    public ResponseEntity<ResponsePartElementLibraryDto> update(
                             @RequestBody @Valid
                             @Parameter(description = "Подэлемент") UpdatePartElementLibraryDto partElementDto) {
        return ResponseEntity.ok().body(service.update(partElementDto));
    }

    @Operation(summary = "Получить все подэлементы элементы")
    @GetMapping("/{elementId}")
    public ResponseEntity<List<ResponsePartElementLibraryDto>> getAll(
                                        @PathVariable(name = "elementId") @NotNull @Positive
                                        @Parameter(description = "Идентификатор типа элемента") Long elementLibraryId) {
        return ResponseEntity.ok().body(service.getAll(elementLibraryId));
    }

    @Operation(summary = "Удаление подэлемента элемента оборудования")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Подэлемент оборудования успешно удален.");
    }
}