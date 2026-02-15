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
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.NewMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseShortMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.UpdateMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.service.MetalHardnessLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Значения допустимых твердости металла",
     description="API для работы с данными значений допустимой твердости металла")
public class MetalHardnessLibraryController {

    private final MetalHardnessLibraryService service;

    @Operation(summary = "Добавить данные допустимой твердости металла ")
    @PostMapping("/hardness")
    public ResponseEntity<ResponseShortMetalHardnessLibraryDto> save(
            @RequestBody @Valid
            @Parameter(name = "Значения допустимой твердости металла") NewMetalHardnessLibraryDto hardnessDto) {
        return ResponseEntity.ok().body(service.save(hardnessDto));
    }

    @Operation(summary = "Изменение значения допустимой твердости металла")
    @PatchMapping("/hardness")
    public ResponseEntity<ResponseShortMetalHardnessLibraryDto> update(
            @RequestBody @Valid
            @Parameter(name = "Значения допустимой твердости металла") UpdateMetalHardnessLibraryDto hardnessDto) {
        return ResponseEntity.ok().body(service.update(hardnessDto));
    }

    @Operation(summary = "Получить допустимое значение")
    @GetMapping("/hardness/{id}")
    public ResponseEntity<ResponseMetalHardnessLibraryDto> get(@PathVariable @NotNull @Positive
                                                                  @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные всех значений твердости металла")
    @GetMapping("/hardnesses")
    public ResponseEntity<List<ResponseShortMetalHardnessLibraryDto>> getAll(
                                                                     @RequestParam(name = "search", required = false)
                                                                     @Parameter(description = "поиск") String search) {
        return ResponseEntity.ok().body(service.getAll(search));
    }

    @Operation(summary = "Удалить данные допустимой твердости металла")
    @DeleteMapping("/hardness/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Допустимая твердость металла удалена.");
    }
}