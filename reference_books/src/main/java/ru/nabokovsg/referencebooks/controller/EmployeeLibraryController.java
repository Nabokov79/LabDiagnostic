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
import ru.nabokovsg.referencebooks.dto.employeeLibrary.NewEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.ResponseEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.ResponseShortEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.dto.employeeLibrary.UpdateEmployeeLibraryDto;
import ru.nabokovsg.referencebooks.service.EmployeeLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Сотрудники подразделений", description="API для работы с данными сотрудников подразделений")
public class EmployeeLibraryController {

    private final EmployeeLibraryService service;

    @Operation(summary = "Добавить сотрудника")
    @PostMapping("/employee")
    public ResponseEntity<ResponseShortEmployeeLibraryDto> save(@RequestBody @Valid
                                                                @Parameter(description = "Сотрудник") NewEmployeeLibraryDto employeeDto) {
        return ResponseEntity.ok().body(service.save(employeeDto));
    }

    @Operation(summary = "Изменить данные сотрудник")
    @PatchMapping("/employee")
    public ResponseEntity<ResponseShortEmployeeLibraryDto> update(@RequestBody @Valid
                                                                  @Parameter(description = "Сотрудник") UpdateEmployeeLibraryDto employeeDto) {
        return ResponseEntity.ok().body(service.update(employeeDto));
    }

    @Operation(summary = "Получить данные сотрудника")
    @GetMapping("/employee/{id}")
    public ResponseEntity<ResponseEmployeeLibraryDto> get(@PathVariable @NotNull @Positive
                                                          @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить данные всех сотрудников филиала")
    @GetMapping("/employees/branch/{id}")
    public ResponseEntity<List<ResponseShortEmployeeLibraryDto>> getAllByBranch(
            @PathVariable(name = "id") @NotNull @Positive
            @Parameter(description = "Идентификатор филиала") Long id,
            @RequestParam(name = "name", required = false)
            @Parameter(description = "ФИО сотрудника") String name) {
        return ResponseEntity.ok().body(service.getAll(id, "branch", name));
    }

    @Operation(summary = "Получить данные всех сотрудников подразделения")
    @GetMapping("/employees/department/{id}")
    public ResponseEntity<List<ResponseShortEmployeeLibraryDto>> getAllByDepartment(
            @PathVariable(name = "id") @NotNull @Positive
            @Parameter(description = "Идентификатор подразделения") Long id,
            @RequestParam(name = "name", required = false)
            @Parameter(description = "ФИО сотрудника") String name) {
        return ResponseEntity.ok().body(service.getAll(id, "department", name));
    }

    @Operation(summary = "Получить данные всех сотрудников источника теплоснабжения")
    @GetMapping("/employees/source/{id}")
    public ResponseEntity<List<ResponseShortEmployeeLibraryDto>> getAllBySource(
            @PathVariable(name = "id") @NotNull @Positive
            @Parameter(description = "Идентификатор источника теплоснабжения") Long id,
            @RequestParam(name = "name", required = false)
            @Parameter(description = "ФИО сотрудника") String name) {
        return ResponseEntity.ok().body(service.getAll(id, "source", name));
    }


    @Operation(summary = "Удалить данные сотрудника")
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные сотрудника удалены.");
    }
}