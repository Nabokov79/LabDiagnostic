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
import ru.nabokovsg.referencebooks.dto.organizationLibrary.NewOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.ResponseOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.ResponseShortOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.UpdateOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.service.OrganizationLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Организация", description="API для работы с данными организации")
public class OrganizationLibraryController {

    private final OrganizationLibraryService service;

    @Operation(summary = "Добавить данные организации")
    @PostMapping("/organization")
    public ResponseEntity<ResponseShortOrganizationLibraryDto> save(@RequestBody @Valid
                                                                 @Parameter(description = "Организация")
                                                             NewOrganizationLibraryDto organizationDto) {
        return ResponseEntity.ok().body(service.save(organizationDto));
    }

    @Operation(summary = "Изменить данные организации")
    @PatchMapping("/organization")
    public ResponseEntity<ResponseShortOrganizationLibraryDto> update(@RequestBody @Valid
                                                               @Parameter(description = "Организация")
                                                                      UpdateOrganizationLibraryDto organizationDto) {
        return ResponseEntity.ok().body(service.update(organizationDto));
    }

    @Operation(summary = "Получение данные организации")
    @GetMapping("/organization/{id}")
    public ResponseEntity<ResponseOrganizationLibraryDto> get(@PathVariable @NotNull @Positive
                                                       @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные всех организаций")
    @GetMapping("/organizations")
    public ResponseEntity<List<ResponseShortOrganizationLibraryDto>> getAll(@RequestParam(name = "name", required = false) @NotNull @Positive
                                                                                @Parameter(description = "Полное или краткое наименование организации") String name) {
        return ResponseEntity.ok().body(service.getAll(name));
    }

    @Operation(summary = "Удалить данные организации")
    @DeleteMapping("/organization/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Организация удалена.");
    }
}