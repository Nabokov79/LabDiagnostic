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
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.NewRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.ResponseRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.UpdateRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.service.RecommendationLibraryService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/LabDiagnostic/library",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Справочник рекомендаций",
        description="API для работы с справочником рекомендаций по эксплуатации оборудования")
public class RecommendationLibraryController {

    private final RecommendationLibraryService service;

    @Operation(summary = "Добавление рекомендации в справочник")
    @PostMapping("/recommendation")
    public ResponseEntity<ResponseRecommendationLibraryDto> save(
            @RequestBody @Valid
            @Parameter(description = "Рекомендация") NewRecommendationLibraryDto recommendationLibraryDto) {
        return ResponseEntity.ok().body(service.save(recommendationLibraryDto));
    }

    @Operation(summary = "Изменение рекомендации в справочнике")
    @PatchMapping("/recommendation")
    public ResponseEntity<ResponseRecommendationLibraryDto> update(
            @RequestBody @Valid
            @Parameter(description = "Рекомендация") UpdateRecommendationLibraryDto recommendationLibraryDto) {
        return ResponseEntity.ok().body(service.update(recommendationLibraryDto));
    }

    @Operation(summary = "Получить рекомендацию")
    @GetMapping("/recommendation/{id}")
    public ResponseEntity<ResponseRecommendationLibraryDto> get(@PathVariable(name = "id") @NotNull @Positive
                                                                @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получить все рекомендации")
    @GetMapping("/recommendations")
    public ResponseEntity<List<ResponseRecommendationLibraryDto>> getAll(
                                                                    @RequestParam(name = "name", required = false)
                                                                    @Parameter(description = "поиск") String name) {
        return ResponseEntity.ok().body(service.getAll(name));
    }

    @Operation(summary = "Удаление рекомендации")
    @DeleteMapping("/recommendation/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Рекомендация удалена из справочника.");
    }
}