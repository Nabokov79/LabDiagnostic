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
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.NewAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseShortAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.UpdateAcceptableMetalHardnessLibraryDto;
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
    public ResponseEntity<ResponseShortAcceptableMetalHardnessLibraryDto> save(
            @RequestBody @Valid
            @Parameter(name = "Значения допустимой твердости металла") NewAcceptableMetalHardnessLibraryDto hardnessDto) {
        return ResponseEntity.ok().body(service.save(hardnessDto));
    }

    @Operation(summary = "Изменение значения допустимой твердости металла")
    @PatchMapping("/hardness")
    public ResponseEntity<ResponseShortAcceptableMetalHardnessLibraryDto> update(
            @RequestBody @Valid
            @Parameter(name = "Значения допустимой твердости металла") UpdateAcceptableMetalHardnessLibraryDto hardnessDto) {
        return ResponseEntity.ok().body(service.update(hardnessDto));
    }

    @Operation(summary = "Получить допустимое значение")
    @GetMapping("/hardness/{id}")
    public ResponseEntity<ResponseAcceptableMetalHardnessLibraryDto> get(@PathVariable @NotNull @Positive
                                                                  @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные всех значений твердости металла")
    @GetMapping("/hardnesses")
    public ResponseEntity<List<ResponseShortAcceptableMetalHardnessLibraryDto>> getAll(
                                                                        @RequestParam(name = "name", required = false)
                                                                        @Parameter(description = "поиск") String name) {
        return ResponseEntity.ok().body(service.getAll(name));
    }

    @Operation(summary = "Удалить данные допустимой твердости металла")
    @DeleteMapping("/hardness/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Допустимая твердость металла удалена.");
    }
}