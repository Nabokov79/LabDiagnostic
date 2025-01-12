package ru.nabokovsg.company.controller;

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
import ru.nabokovsg.company.dto.heatSupplyArea.NewHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ResponseHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ResponseShortHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.UpdateHeatSupplyAreaDto;
import ru.nabokovsg.company.service.HeatSupplyAreaService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/company/heat/supply/area",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name = "Район теплоснабжения",
        description = "API для работы с данными района теплоснабжения")
public class HeatSupplyAreaController {

    private final HeatSupplyAreaService service;

    @Operation(summary = "Добавление данных района теплоснабжения")
    @PostMapping
    public ResponseEntity<ResponseShortHeatSupplyAreaDto> save(@RequestBody @Valid
                                        @Parameter(description = "Район теплоснабжения") NewHeatSupplyAreaDto areaDto) {
        return ResponseEntity.ok().body(service.save(areaDto));
    }

    @Operation(summary = "Изменение данных района теплоснабжения")
    @PatchMapping
    public ResponseEntity<ResponseShortHeatSupplyAreaDto> update(@RequestBody @Valid
                                     @Parameter(description = "Район теплоснабжения") UpdateHeatSupplyAreaDto areaDto) {
        return ResponseEntity.ok().body(service.update(areaDto));
    }

    @Operation(summary = "Получение данных района теплоснабжения")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseHeatSupplyAreaDto> get(@PathVariable @NotNull @Positive
                                                                    @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение кратких сведений о всех района теплоснабжения")
    @GetMapping
    public ResponseEntity<List<ResponseShortHeatSupplyAreaDto>> getAll(
                                           @RequestParam(name = "id", required = false)
                                           @Parameter(description = "Идентификатор филиала") Long branchId,
                                           @RequestParam(required = false)
                                           @Parameter(description = "Наименование района теплоснабжения") String name) {
        return ResponseEntity.ok().body(service.getAll(branchId, name));
    }

    @Operation(summary = "Удаление данных района теплоснабжения")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                                                @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные района теплоснабжения удалены.");
    }
}