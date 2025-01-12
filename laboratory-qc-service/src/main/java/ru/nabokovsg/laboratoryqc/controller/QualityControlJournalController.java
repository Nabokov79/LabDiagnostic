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
import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.NewQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.ResponseQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.dto.qualityControlJournal.UpdateQualityControlJournalDto;
import ru.nabokovsg.laboratoryqc.model.SearchDataJournalCompletedWork;
import ru.nabokovsg.laboratoryqc.service.QualityControlJournalService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/laboratory/QCL/journal/control",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Журнал контроля качества сварных соединения",
        description="API для работы с журналом контроля качества сварных соединения")
public class QualityControlJournalController {

    private final QualityControlJournalService service;

    @Operation(summary = "Добавление записи в журнал")
    @PostMapping
    public ResponseEntity<ResponseQualityControlJournalDto> save(@RequestBody @Valid
                                                                @Parameter(description = "Запись в журнал")
                                                                 NewQualityControlJournalDto journalDto) {
        return ResponseEntity.ok().body(service.save(journalDto));
    }

    @Operation(summary = "Изменение записи в журнале")
    @PatchMapping
    public ResponseEntity<ResponseQualityControlJournalDto> update(@RequestBody @Valid
                                                                  @Parameter(description = "Запись в журнал")
                                                                   UpdateQualityControlJournalDto journalDto) {
        return ResponseEntity.ok().body(service.update(journalDto));
    }

    @Operation(summary = "Получить запись")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseQualityControlJournalDto> get(@PathVariable @NotNull @Positive
                                                                @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все оборудование котельной, ЦТП")
    @GetMapping
    public ResponseEntity<List<ResponseQualityControlJournalDto>> getAll(
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