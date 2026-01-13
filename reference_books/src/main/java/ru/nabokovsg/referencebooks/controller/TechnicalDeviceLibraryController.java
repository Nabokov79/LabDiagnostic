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
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.NewTechnicalDeviceLibraryDto;
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.ResponseTechnicalDeviceLibraryDto;
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.UpdateTechnicalDeviceLibraryDto;
import ru.nabokovsg.referencebooks.service.TechnicalDeviceLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Техническое устройство",
        description="API для работы с данными объектов и технических устройств участка тепловой сети")
public class TechnicalDeviceLibraryController {

    private final TechnicalDeviceLibraryService service;

    @Operation(summary = "Добавить данные технического устройства")
    @PostMapping("/device")
    public ResponseEntity<ResponseTechnicalDeviceLibraryDto> save(@RequestBody @Valid
                                                             @Parameter(description = "Техническое устройство")
                                                             NewTechnicalDeviceLibraryDto deviceDto) {
        return ResponseEntity.ok().body(service.save(deviceDto));
    }

    @Operation(summary = "Изменить данные технического устройства")
    @PatchMapping("/device")
    public ResponseEntity<ResponseTechnicalDeviceLibraryDto> update(@RequestBody @Valid
                                                               @Parameter(description = "Техническое устройство")
                                                               UpdateTechnicalDeviceLibraryDto deviceDto) {
        return ResponseEntity.ok().body(service.update(deviceDto));
    }

    @Operation(summary = "Получить данные технического устройства")
    @GetMapping("/device/{id}")
    public ResponseEntity<ResponseTechnicalDeviceLibraryDto> get(@PathVariable @NotNull @Positive
                                                            @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данные всех технических устройств участка тепловой сети")
    @GetMapping("/devices/{id}")
    public ResponseEntity<List<ResponseTechnicalDeviceLibraryDto>> getAll(
            @PathVariable(name = "id") @NotNull @Positive
            @Parameter(description = "Идентификатор участка тепловой сети") Long id,
            @RequestParam(name = "name", required = false)
            @Parameter(description = "Полное или краткое наименование тех. устройства") String name) {
        return ResponseEntity.ok().body(service.getAll(id, name));
    }

    @Operation(summary = "Удалить данные технического устройства")
    @DeleteMapping("/device/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Техническое устройство удалено.");
    }
}