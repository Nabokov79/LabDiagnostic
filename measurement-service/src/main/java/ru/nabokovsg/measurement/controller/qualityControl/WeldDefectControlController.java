package ru.nabokovsg.measurement.controller.qualityControl;

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
import ru.nabokovsg.measurement.dto.weldDefectControl.NewWeldDefectControlDto;
import ru.nabokovsg.measurement.dto.weldDefectControl.ResponseWeldDefectControlDto;
import ru.nabokovsg.measurement.dto.weldDefectControl.UpdateWeldDefectControlDto;
import ru.nabokovsg.measurement.service.qualityControl.WeldDefectControlService;

import java.util.List;

@RestController
@RequestMapping(
        value =  "/WorkVisionWeb/measurement/control/visual",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Визуальный и измерительный контроль сварного соединения",
        description="API для работы с результатами визуального и измерительнго контроля сварного соединения")
public class WeldDefectControlController {

    private final WeldDefectControlService service;

    @Operation(summary = "Добавить данные результата измерения")
    @PostMapping
    public ResponseEntity<ResponseWeldDefectControlDto> save(@RequestBody @Valid
                                                                    @Parameter(name = "Данные измеренния дефекта")
                                                                    NewWeldDefectControlDto defectDto) {
        return ResponseEntity.ok().body(service.save(defectDto));
    }

    @Operation(summary = "Изменить данные результата измерения дефекта")
    @PatchMapping
    public ResponseEntity<ResponseWeldDefectControlDto> update(@RequestBody @Valid
                                                                      @Parameter(name = "Данные измеренния дефекта")
                                                               UpdateWeldDefectControlDto defectDto) {
        return ResponseEntity.ok().body(service.update(defectDto));
    }

    @Operation(summary = "Получить измеренныый дефект сварного соединения")
    @GetMapping("{id}")
    public ResponseEntity<ResponseWeldDefectControlDto> get(@PathVariable(name = "id") @NotNull @Positive
                                                                          @Parameter(name = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные измеренных дефектов по идентификатору оборудования")
    @GetMapping
    public ResponseEntity<List<ResponseWeldDefectControlDto>> getAll(
                                        @RequestParam(name = "equipmentId") @NotNull @Positive
                                        @Parameter(description = "Идентификатор типа оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAll(equipmentId));
    }

    @Operation(summary = "Удалить измеренный дефект")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Дефект успешно удален.");
    }
}