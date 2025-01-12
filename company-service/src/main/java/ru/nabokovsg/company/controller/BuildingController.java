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
import ru.nabokovsg.company.dto.building.NewBuildingDto;
import ru.nabokovsg.company.dto.building.ResponseBuildingDto;
import ru.nabokovsg.company.dto.building.ResponseShortBuildingDto;
import ru.nabokovsg.company.dto.building.UpdateBuildingDto;
import ru.nabokovsg.company.service.BuildingService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/company/building",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Производственное здание",
        description="API для работы с информацией о производственном здании(котельная, ЦТП)")
public class BuildingController {

    private final BuildingService service;

    @Operation(summary = "Добавление новой информации")
    @PostMapping
    public ResponseEntity<ResponseShortBuildingDto> save(@RequestBody @Valid
                                       @Parameter(description = "Производственное здание") NewBuildingDto buildingDto) {
        return ResponseEntity.ok().body(service.save(buildingDto));
    }

    @Operation(summary = "Изменение данных котельной(цтп)")
    @PatchMapping
    public ResponseEntity<ResponseShortBuildingDto> update(@RequestBody @Valid
                                    @Parameter(description = "Производственное здание") UpdateBuildingDto buildingDto) {
        return ResponseEntity.ok().body(service.update(buildingDto));
    }

    @Operation(summary = "Получение данных всех котельных(цтп)")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBuildingDto> get(@PathVariable @NotNull @Positive
                                                   @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных всех котельных(цтп)")
    @GetMapping
    public ResponseEntity<List<ResponseShortBuildingDto>> getAll(@RequestParam(name = "id", required = false)
                                         @Parameter(description = "Идентификатор эксплуатационного участка") Long id) {
        return ResponseEntity.ok().body(service.getAll(id));
    }

    @Operation(summary = "Удаление данных котельной(цтп)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные котельной(ЦТП) успешно удалены.");
    }
}