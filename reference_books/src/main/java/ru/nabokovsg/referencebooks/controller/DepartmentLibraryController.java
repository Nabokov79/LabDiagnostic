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
import ru.nabokovsg.referencebooks.dto.departmentLibrary.NewDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.ResponseDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.ResponseShortDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.departmentLibrary.UpdateDepartmentLibraryDto;
import ru.nabokovsg.referencebooks.service.DepartmentLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Подразделение",
        description="API для работы с данными подразделения")
public class DepartmentLibraryController {

    private final DepartmentLibraryService service;

    @Operation(summary = "Добавить данные подразделения")
    @PostMapping("/department")
    public ResponseEntity<ResponseShortDepartmentLibraryDto> save(@RequestBody @Valid
                                                      @Parameter(description = "Подразделение")
                                                      NewDepartmentLibraryDto departmentDto) {
        return ResponseEntity.ok().body(service.save(departmentDto));
    }

    @Operation(summary = "Изменить данные подразделения")
    @PatchMapping("/department")
    public ResponseEntity<ResponseShortDepartmentLibraryDto> update(@RequestBody @Valid
                                                        @Parameter(description = "Подразделение")
                                                               UpdateDepartmentLibraryDto departmentDto) {
        return ResponseEntity.ok().body(service.update(departmentDto));
    }

    @Operation(summary = "Получить данные подразделения")
    @GetMapping("/department/{id}")
    public ResponseEntity<ResponseDepartmentLibraryDto> get(@PathVariable @NotNull @Positive
                                                         @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данные всх подразделений")
    @GetMapping("/departments/{id}")
    public ResponseEntity<List<ResponseShortDepartmentLibraryDto>> getAll(
                                                @PathVariable(name = "id") @NotNull @Positive
                                                @Parameter(description = "Идентификатор филиала") Long id,
                                                @RequestParam(name = "name", required = false)
                                                @Parameter(description = "Наименование подразделения") String name) {
        return ResponseEntity.ok().body(service.getAll(id, name));
    }

    @Operation(summary = "Удалить данные подразделения")
    @DeleteMapping("/department/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Подразделение удалено.");
    }
}