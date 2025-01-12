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
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.NewQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.ResponseQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlLaboratoryCertificate.UpdateQualityControlLaboratoryCertificateDto;
import ru.nabokovsg.laboratoryqc.service.QualityControlLaboratoryCertificateService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/laboratory/QCL/certificate",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Аттестация лаборатории НК",
        description="API для работы с данными аттестации лаборатории НК")
public class QualityControlLaboratoryCertificateController {

    private final QualityControlLaboratoryCertificateService service;

    @Operation(summary = "Добавление сведений об аттестации")
    @PostMapping
    public ResponseEntity<ResponseQualityControlLaboratoryCertificateDto> save(
                                             @RequestBody @Valid @Parameter(description = "Сведения об аттестации")
                                             NewQualityControlLaboratoryCertificateDto certificateDto) {
        return ResponseEntity.ok().body(service.save(certificateDto));
    }

    @Operation(summary = "Изменение сведений об аттестации")
    @PatchMapping
    public ResponseEntity<ResponseQualityControlLaboratoryCertificateDto> update(
                                               @RequestBody @Valid @Parameter(description = "Сведения об аттестации")
                                               UpdateQualityControlLaboratoryCertificateDto certificateDto) {
        return ResponseEntity.ok().body(service.update(certificateDto));
    }

    @Operation(summary = "Получить сведений об аттестации")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseQualityControlLaboratoryCertificateDto> get(@PathVariable @NotNull @Positive
                                                                    @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение всех сохраненных аттестаций")
    @GetMapping
    public ResponseEntity<List<ResponseQualityControlLaboratoryCertificateDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление сведений об аттестации")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                                                @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Сведения об аттестации успешно удалены.");
    }
}