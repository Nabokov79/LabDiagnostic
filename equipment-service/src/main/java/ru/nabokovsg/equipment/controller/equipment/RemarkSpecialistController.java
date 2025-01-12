package ru.nabokovsg.equipment.controller.equipment;

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
import ru.nabokovsg.equipment.dto.remarkSpecialist.NewRemarkSpecialistDto;
import ru.nabokovsg.equipment.dto.remarkSpecialist.ResponseRemarkSpecialistDto;
import ru.nabokovsg.equipment.dto.remarkSpecialist.UpdateRemarkSpecialistDto;
import ru.nabokovsg.equipment.service.equipment.RemarkSpecialistService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/equipment/remark",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Замечание по техническому состоянию элементов/подэлементов оборудования",
        description="API для работы с замечаниями по техническому состоянию элементов/подэлементов оборудования")
public class RemarkSpecialistController {

    private final RemarkSpecialistService service;

    @Operation(summary = "Добавить замечание")
    @PostMapping
    public ResponseEntity<ResponseRemarkSpecialistDto> save(@RequestBody @Valid
                                                             @Parameter(name = "Замечание")
                                                            NewRemarkSpecialistDto remarkDto) {
        return ResponseEntity.ok().body(service.save(remarkDto));
    }

    @Operation(summary = "Изменить замечание")
    @PatchMapping
    public ResponseEntity<ResponseRemarkSpecialistDto> update(@RequestBody @Valid
                                                               @Parameter(name = "Замечание")
                                                              UpdateRemarkSpecialistDto remarkDto) {
        return ResponseEntity.ok().body(service.update(remarkDto));
    }

    @Operation(summary = "Получить данные замечаний по идентификатору записи оборудования")
    @GetMapping
    public ResponseEntity<List<ResponseRemarkSpecialistDto>> getAll(
                                            @RequestParam(name = "id") @NotNull @Positive
                                            @Parameter(description = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAll(equipmentId));
    }

    @Operation(summary = "Удалить замечание")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Замечание успешно удалено.");
    }
}