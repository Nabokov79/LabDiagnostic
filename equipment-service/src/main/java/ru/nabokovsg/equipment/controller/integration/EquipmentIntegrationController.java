package ru.nabokovsg.equipment.controller.integration;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.equipment.dto.integration.EquipmentDto;
import ru.nabokovsg.equipment.service.integration.EquipmentIntegrationService;

@RestController
@RequestMapping(
        value = "/equipment/integration",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Контроллер сведений об оборудовании",
        description="API для работы с данными оборудования для выполнения расчетов результатов измерения")
public class EquipmentIntegrationController {

    private final EquipmentIntegrationService service;

    @Operation(summary = "Получить сведения об оборудовании из библиотеки оборудования или оборудование котельной, ЦТП")
    @GetMapping
    public ResponseEntity<EquipmentDto> getIds(@RequestParam(name = "elementId") @NotNull @Positive
                                            @Parameter(description = "Идентификатор элемента") Long elementId
                                          , @RequestParam(name = "partElementId")
                                            @Parameter(description = "Идентификатор подэлемента") Long partElementId) {
        return ResponseEntity.ok().body(service.getIds(elementId, partElementId));
    }

    @Operation(summary = "Получить сведения об оборудовании из библиотеки оборудования или оборудование котельной, ЦТП")
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDto> getByEquipmentId(@PathVariable(name = "id") @NotNull @Positive
                                                      @Parameter(description = "Идентификатор оборудования") Long id) {
        return ResponseEntity.ok().body(service.getByEquipmentId(id));
    }
}