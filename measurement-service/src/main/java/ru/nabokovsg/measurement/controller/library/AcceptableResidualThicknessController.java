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
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.NewAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.ResponseAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.UpdateAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.service.library.AcceptableResidualThicknessService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/measurement/library/thickness",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные минимальных допустимых толщин стенок элементов оборудования",
        description="API для работы с данными норм минимальных допустимых стенок элементов оборудования")
public class AcceptableResidualThicknessController {

    private final AcceptableResidualThicknessService service;

    @Operation(summary = "Добавить новые данные допустимых толщины стенки")
    @PostMapping
    public ResponseEntity<ResponseAcceptableResidualThicknessDto> save(
                        @RequestBody @Valid
                        @Parameter(name = "Допустимая толщины стенки") NewAcceptableResidualThicknessDto thicknessDto) {
        return ResponseEntity.ok().body(service.save(thicknessDto));
    }

    @Operation(summary = "Изменение данных допустимых толщины стенки")
    @PatchMapping
    public ResponseEntity<ResponseAcceptableResidualThicknessDto> update(
                    @RequestBody @Valid
                    @Parameter(name = "Допустимая толщины стенки") UpdateAcceptableResidualThicknessDto thicknessDto) {
        return ResponseEntity.ok().body(service.update(thicknessDto));
    }

    @Operation(summary = "Получить допустимое измерение")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseAcceptableResidualThicknessDto> get(@PathVariable @NotNull @Positive
                                                                      @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные допустимых толщин толщины стенки элементов оборудования")
    @GetMapping
    public ResponseEntity<List<ResponseAcceptableResidualThicknessDto>> getAll(
                                 @RequestParam(name = "id") @NotNull @Positive
                                 @Parameter(description = "Идентификатор типа оборудования") Long equipmentLibraryId) {
        return ResponseEntity.ok().body(service.getAll(equipmentLibraryId));
    }

    @Operation(summary = "Удалить данные допустимых толщины стенки")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные допустимых толщины стенки элемента оборудования успешно удалены.");
    }
}