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
import ru.nabokovsg.company.dto.organization.NewOrganizationDto;
import ru.nabokovsg.company.dto.organization.ResponseOrganizationDto;
import ru.nabokovsg.company.dto.organization.ResponseShortOrganizationDto;
import ru.nabokovsg.company.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.company.service.OrganizationService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/company/organization",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Организация",
        description="API для работы с данными организации")
public class OrganizationController {

    private final OrganizationService service;

    @Operation(summary = "Добавление данных организации")
    @PostMapping
    public ResponseEntity<ResponseShortOrganizationDto> save(@RequestBody @Valid
                                           @Parameter(description = "Организация") NewOrganizationDto organizationDto) {
        return ResponseEntity.ok().body(service.save(organizationDto));
    }

    @Operation(summary = "Изменение данных организации")
    @PatchMapping
    public ResponseEntity<ResponseShortOrganizationDto> update(@RequestBody @Valid
                                        @Parameter(description = "Организация") UpdateOrganizationDto organizationDto) {
        return ResponseEntity.ok().body(service.update(organizationDto));
    }

    @Operation(summary = "Получение полных данных организации")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrganizationDto> get(@PathVariable @NotNull @Positive
                                                                    @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение кратких сведений об организациях")
    @GetMapping
    public ResponseEntity<List<ResponseShortOrganizationDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление данных организации")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                                                @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Организация успешно удалена.");
    }
}