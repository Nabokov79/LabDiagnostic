package ru.nabokovsg.measurement.controller.library;

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
import ru.nabokovsg.measurement.dto.repairLibrary.NewRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.ResponseRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.ResponseShortRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.UpdateRepairLibraryDto;
import ru.nabokovsg.measurement.service.library.RepairLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/measurement/library/repair",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Ремонт элементов оборудования",
        description="API для работы с данными ремонта элементов оборудования")
public class RepairLibraryController {

    private final RepairLibraryService service;

    @Operation(summary = "Добавление способа ремонта")
    @PostMapping
    public ResponseEntity<ResponseRepairLibraryDto> save(
               @RequestBody @Valid @Parameter(description = "Тип ремонта элемента") NewRepairLibraryDto repairDto) {
        return ResponseEntity.ok().body(service.save(repairDto));
    }

    @Operation(summary = "Изменение данных способа ремонта")
    @PatchMapping
    public ResponseEntity<ResponseRepairLibraryDto> update(
            @RequestBody @Valid @Parameter(description = "Тип ремонта элемента") UpdateRepairLibraryDto repairDto) {
        return ResponseEntity.ok().body(service.update(repairDto));
    }

    @Operation(summary = "Получить типы ремонта элементов оборудования по его типу")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseRepairLibraryDto> get(@PathVariable @NotNull @Positive
                                                                @Parameter(name = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить типы ремонта элементов оборудования по его типу")
    @GetMapping
    public ResponseEntity<List<ResponseShortRepairLibraryDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удалить тип ремонта")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Тип ремонта элемента оборудования успешно удален.");
    }
}