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
import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.NewJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.ResponseJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.dto.journalDiagnoticsEquipment.UpdateJournalDiagnoticsEquipmentDto;
import ru.nabokovsg.laboratoryqc.model.SearchDataJournalCompletedWork;
import ru.nabokovsg.laboratoryqc.service.JournalDiagnoticsEquipmentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/laboratory/QCL/journal/diagnostics/equipment",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Журнал диагностики оборудования и трубопроводов котельных и ЦТП",
        description="API для работы с журналом диагностики оборудования и трубопроводов котельных и ЦТП")
public class JournalDiagnoticsEquipmentController {

    private final JournalDiagnoticsEquipmentService service;

    @Operation(summary = "Добавление записи в журнал")
    @PostMapping
    public ResponseEntity<ResponseJournalDiagnoticsEquipmentDto> save(@RequestBody @Valid
                                                                @Parameter(description = "Запись в журнал")
                                                                      NewJournalDiagnoticsEquipmentDto journalDto) {
        return ResponseEntity.ok().body(service.save(journalDto));
    }

    @Operation(summary = "Изменение записи в журнале")
    @PatchMapping
    public ResponseEntity<ResponseJournalDiagnoticsEquipmentDto> update(@RequestBody @Valid
                                                                  @Parameter(description = "Запись в журнал")
                                                                    UpdateJournalDiagnoticsEquipmentDto journalDto) {
        return ResponseEntity.ok().body(service.update(journalDto));
    }

    @Operation(summary = "Получить запись")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseJournalDiagnoticsEquipmentDto> get(@PathVariable @NotNull @Positive
                                                                    @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все оборудование котельной, ЦТП")
    @GetMapping
    public ResponseEntity<List<ResponseJournalDiagnoticsEquipmentDto>> getAll(
                                  @RequestParam(value = "start", required = false)
                                  @Parameter(description = "Начало периода") LocalDate startPeriod
                                , @RequestParam(value = "end", required = false)
                                  @Parameter(description = "Окончание периода") LocalDate endPeriod
                                , @RequestParam(name = "addressId", required = false)
                                  @Parameter(description = "Идентификатор адреса") Long addressId
                                , @RequestParam(name = "employeesId", required = false)
                                  @Parameter(description = "Идентификатор сотрудника") Long employeesId
                                , @RequestParam(name = "equipmentId", required = false)
                                  @Parameter(description = "Идентификатор оборудования") Long equipmentId) {
        SearchDataJournalCompletedWork search = new SearchDataJournalCompletedWork.Builder()
                                                                                    .startPeriod(startPeriod)
                                                                                    .endPeriod(endPeriod)
                                                                                    .addressId(addressId)
                                                                                    .employeesId(employeesId)
                                                                                    .equipmentId(equipmentId)
                                                                                    .build();
        return ResponseEntity.ok().body(service.getAll(search));
    }

    @Operation(summary = "Удалить запись")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Запись успешно удалена.");
    }
}