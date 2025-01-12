package ru.nabokovsg.laboratoryqc.controller;

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
import ru.nabokovsg.laboratoryqc.dto.qclEmployee.NewQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployee.ResponseQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployee.UpdateQCLEmployeeDto;
import ru.nabokovsg.laboratoryqc.service.QCLEmployeeService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/laboratory/QCL/employee",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Журнал диагностики оборудования и трубопроводов котельных и ЦТП",
        description="API для работы с журналом диагностики оборудования и трубопроводов котельных и ЦТП")
public class QCLEmployeeController {

    private final QCLEmployeeService service;

    @Operation(summary = "Добавление данных нового сотрудника")
    @PostMapping
    public ResponseEntity<ResponseQCLEmployeeDto> save(@RequestBody @Valid
                                                 @Parameter(description = "Сотрудник") NewQCLEmployeeDto employeeDto) {
        return ResponseEntity.ok().body(service.save(employeeDto));
    }

    @Operation(summary = "Изменение данных сотрудника")
    @PatchMapping
    public ResponseEntity<ResponseQCLEmployeeDto> update(@RequestBody @Valid
                                              @Parameter(description = "Сотрудник") UpdateQCLEmployeeDto employeeDto) {
        return ResponseEntity.ok().body(service.update(employeeDto));
    }

    @Operation(summary = "Получение данных сотрудника")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseQCLEmployeeDto> get(@PathVariable @NotNull @Positive
                                                          @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных всех сотрудников")
    @GetMapping
    public ResponseEntity<List<ResponseQCLEmployeeDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление данных сотрудника")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable @NotNull @Positive
                                       @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные сотрудника удалены.");
    }
}