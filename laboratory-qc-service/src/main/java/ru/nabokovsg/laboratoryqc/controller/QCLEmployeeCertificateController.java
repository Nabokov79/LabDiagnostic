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
import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.NewQCLEmployeeCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.ResponseQCLEmployeeCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qclEmployeeCertificate.UpdateQCLEmployeeCertificateDto;
import ru.nabokovsg.laboratoryqc.service.QCLEmployeeCertificateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/laboratory/QCL/certificate/employee",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name= "Аттестация сотрудников лаборатории НК",
        description="API для работы с данными аттестация сотрудников лаборатории НК, по видам контроля")
public class QCLEmployeeCertificateController {

    private final QCLEmployeeCertificateService service;

    @Operation(summary = "Добавление информацию об аттестации сотрудника")
    @PostMapping
    public ResponseEntity<ResponseQCLEmployeeCertificateDto> save(@RequestBody @Valid
                                                                  @Parameter(description = "Аттестация сутрудника")
                                                                  NewQCLEmployeeCertificateDto certificateDto) {
        return ResponseEntity.ok().body(service.save(certificateDto));
    }

    @Operation(summary = "Изменение информацию об аттестации сотрудника")
    @PatchMapping
    public ResponseEntity<ResponseQCLEmployeeCertificateDto> update(@RequestBody @Valid
                                                                    @Parameter(description = "Аттестация сотрудника")
                                                                    UpdateQCLEmployeeCertificateDto certificateDto) {
        return ResponseEntity.ok().body(service.update(certificateDto));
    }

    @Operation(summary = "Получение информацию об аттестации сотрудника")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseQCLEmployeeCertificateDto> get(@PathVariable @NotNull @Positive
                                                      @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение информации об аттестациях сотрудника по всем видам контроля")
    @GetMapping
    public ResponseEntity<List<ResponseQCLEmployeeCertificateDto>> getAll(@RequestParam(name = "id") @NotNull @Positive
                                                @Parameter(description = "Идентификатор сотрудника") Long employeeId) {
        return ResponseEntity.ok().body(service.getAll(employeeId));
    }

    @Operation(summary = "Удаление данных сотрудника")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                                 @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Данные аттестации сотрудника успешно удалены.");
    }
}