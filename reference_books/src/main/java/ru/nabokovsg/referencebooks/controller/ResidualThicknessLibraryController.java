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
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.NewResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseShortResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.UpdateResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.service.ResidualThicknessLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные минимальных допустимых толщин стенок элементов оборудования",
        description="API для работы с данными норм минимальных допустимых стенок элементов оборудования")
public class ResidualThicknessLibraryController {

    private final ResidualThicknessLibraryService service;

    @Operation(summary = "Добавить значение допустимой толщины")
    @PostMapping("/thickness/residual")
    public ResponseEntity<ResponseShortResidualThicknessLibraryDto> save(
                                                 @RequestBody @Valid @Parameter(name = "Значение допустимой толщины")
                                                 NewResidualThicknessLibraryDto thicknessDto) {
        return ResponseEntity.ok().body(service.save(thicknessDto));
    }

    @Operation(summary = "Изменение значение допустимой толщины")
    @PatchMapping("/thickness/residual")
    public ResponseEntity<ResponseShortResidualThicknessLibraryDto> update(
                                                @RequestBody @Valid @Parameter(name = "Значение допустимой толщины")
                                                UpdateResidualThicknessLibraryDto thicknessDto) {
        return ResponseEntity.ok().body(service.update(thicknessDto));
    }

    @Operation(summary = "Изменение значение допустимой толщины")
    @GetMapping("/thickness/residual/{id}")
    public ResponseEntity<ResponseResidualThicknessLibraryDto> get(@PathVariable @NotNull @Positive
                                                              @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все значения допустимых толщин")
    @GetMapping("/thicknesses/residual")
    public ResponseEntity<List<ResponseShortResidualThicknessLibraryDto>> getAll(
                                                                        @RequestParam(name = "name", required = false)
                                                                        @Parameter(description = "поиск") String name) {
        return ResponseEntity.ok().body(service.getAll(name));
    }

    @Operation(summary = "Удалить значение допустимой толщины")
    @DeleteMapping("/thickness/residual/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Значение допустимой толщины удалено.");
    }
}