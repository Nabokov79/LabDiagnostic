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
import ru.nabokovsg.laboratoryqc.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.laboratoryqc.dto.measuringTool.ResponseMeasuringToolDto;
import ru.nabokovsg.laboratoryqc.dto.measuringTool.UpdateMeasuringToolDto;
import ru.nabokovsg.laboratoryqc.service.MeasuringToolService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/laboratory/QCL/tool",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Инструменты и приборы измерения",
     description="API для работы с инструментами и приборами измерения")
public class MeasuringToolController {

    private final MeasuringToolService service;

    @Operation(summary = "Добавление данных инструмента(прибора) измерения")
    @PostMapping
    public ResponseEntity<ResponseMeasuringToolDto> save(
                         @RequestBody @Valid
                         @Parameter(description = "Инструмент(прибор) измерения") NewMeasuringToolDto measuringToolDto) {
        return ResponseEntity.ok().body(service.save(measuringToolDto));
    }

    @Operation(summary = "Изменение данных инструмента(прибора) измерения")
    @PatchMapping
    public ResponseEntity<ResponseMeasuringToolDto> update(
                         @RequestBody @Valid
                         @Parameter(description = "Инструмент(прибор) измерения") UpdateMeasuringToolDto measuringToolDto) {
        return ResponseEntity.ok().body(service.update(measuringToolDto));
    }

    @Operation(summary = "Получить инструмент(прибор) измерения")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMeasuringToolDto> get(@PathVariable @NotNull @Positive
                                                        @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить инструменты(приборы) измерения")
    @GetMapping
    public ResponseEntity<List<ResponseMeasuringToolDto>> getAll(
     @RequestParam(required = false) @Parameter(description = "Текст поиска") String search,
     @RequestParam(required = false) @Parameter(description = "Дата начала эксплуатации") LocalDate exploitation,
     @RequestParam(required = false) @Parameter(description = "Идентификатор сотрудника") Long employeeId) {
        return ResponseEntity.ok().body(service.getAll(search, exploitation, employeeId));
    }

    @Operation(summary = "Удаление инструмента(прибора) измерения")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Инструмент(прибор) измерения успешно удален.");
    }
}