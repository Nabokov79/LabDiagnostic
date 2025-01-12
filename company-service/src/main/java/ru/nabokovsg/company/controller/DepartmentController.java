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
import ru.nabokovsg.company.dto.department.NewDepartmentDto;
import ru.nabokovsg.company.dto.department.ResponseDepartmentDto;
import ru.nabokovsg.company.dto.department.ResponseShortDepartmentDto;
import ru.nabokovsg.company.dto.department.UpdateDepartmentDto;
import ru.nabokovsg.company.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/company/department",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Подразделение организации",
        description="API для работы с данными подразделения организации")
public class DepartmentController {

    private final DepartmentService service;

    @Operation(summary = "Добавление данных подразделения организации")
    @PostMapping
    public ResponseEntity<ResponseShortDepartmentDto> save(@RequestBody @Valid
                                             @Parameter(description = "Подразделение") NewDepartmentDto departmentDto) {
        return ResponseEntity.ok().body(service.save(departmentDto));
    }

    @Operation(summary = "Изменение данных подразделения организации")
    @PatchMapping
    public ResponseEntity<ResponseShortDepartmentDto> update(@RequestBody @Valid
                                          @Parameter(description = "Подразделение") UpdateDepartmentDto departmentDto) {
        return ResponseEntity.ok().body(service.update(departmentDto));
    }

    @Operation(summary = "Получение данных подразделения организации")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDepartmentDto> get(@PathVariable @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение кратких сведений о подразделении филиала")
    @GetMapping
    public ResponseEntity<List<ResponseShortDepartmentDto>> getAll(
                                                   @RequestParam(name = "id", required = false)
                                                   @Parameter(description = "Идентификатор филиала") Long branchId,
                                                   @RequestParam(name = "name", required = false)
                                                   @Parameter(description = "Наименование подразделения") String name) {
        return ResponseEntity.ok().body(service.getAll(branchId, name));
    }

    @Operation(summary = "Удаление данных подразделения филиала")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Подразделение успешно удалено.");
    }
}