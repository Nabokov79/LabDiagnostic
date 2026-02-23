package ru.nabokovsg.equipmentunit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.equipmentunit.dto.client.ElementLibraryDto;
import ru.nabokovsg.equipmentunit.dto.client.EquipmentLibraryDto;
import ru.nabokovsg.equipmentunit.dto.client.PartElementLibraryDto;
import ru.nabokovsg.equipmentunit.service.EquipmentLibraryService;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/equipment/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Справочные данные оборудования, элементов, подэлементов",
        description="API для работы синхронизации наименований оборудования, элементов, подэлементов со справочником")
public class EquipmentLibraryController {

    private final EquipmentLibraryService service;

    @Operation(summary = "Изменить наименование типа оборудования")
    @PatchMapping
    public ResponseEntity<HttpStatus> updateEquipment(
            @RequestBody @Parameter(description = "Тип оборудования") EquipmentLibraryDto equipmentDto) {
        service.updateEquipment(equipmentDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Изменение наименование типа элемента")
    @PatchMapping("/element")
    public ResponseEntity<HttpStatus> updateElement(
            @RequestBody @Parameter(description = "Тип элемента") ElementLibraryDto elementLibraryDto) {
        service.updateElement(elementLibraryDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Изменение наименования типа подэлемента")
    @PatchMapping("/element/part")
    public ResponseEntity<HttpStatus> updatePartElement(
            @RequestBody @Parameter(description = "Тип подэлемента") PartElementLibraryDto partElementLibraryDto) {
        service.updatePartElement(partElementLibraryDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}