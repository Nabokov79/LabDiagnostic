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
import ru.nabokovsg.measurement.dto.defectLibrary.NewDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.ResponseDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.ResponseShortDefectLibraryDto;
import ru.nabokovsg.measurement.dto.defectLibrary.UpdateDefectLibraryDto;
import ru.nabokovsg.measurement.service.library.DefectLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/measurement/library/defect",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Библиотека дефектов",
        description="API для работы с библиотекой дефектами")
public class DefectLibraryController {

    private final DefectLibraryService service;

    @Operation(summary = "Добавление новых дефектов оборудования")
    @PostMapping
    public ResponseEntity<ResponseDefectLibraryDto> save(
                             @RequestBody @Valid @Parameter(description = "Дефект") NewDefectLibraryDto defectDto) {
        return ResponseEntity.ok().body(service.save(defectDto));
    }

    @Operation(summary = "Изменение данных дефектов оборудования")
    @PatchMapping
    public ResponseEntity<ResponseDefectLibraryDto> update(
                          @RequestBody @Valid @Parameter(description = "Дефект") UpdateDefectLibraryDto defectDto) {
        return ResponseEntity.ok().body(service.update(defectDto));
    }

    @Operation(summary = "Получить дефект")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDefectLibraryDto> get(@PathVariable @NotNull @Positive
                                                                @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить дефекты")
    @GetMapping
    public ResponseEntity<List<ResponseShortDefectLibraryDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удалить дефект")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные дефекта успешно удалены.");
    }
}