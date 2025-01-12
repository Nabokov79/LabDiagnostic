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
import ru.nabokovsg.equipment.dto.recommendationSpecialist.NewRecommendationSpecialistDto;
import ru.nabokovsg.equipment.dto.recommendationSpecialist.ResponseRecommendationSpecialistDto;
import ru.nabokovsg.equipment.dto.recommendationSpecialist.UpdateRecommendationSpecialistDto;
import ru.nabokovsg.equipment.service.equipment.RecommendationSpecialistService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/equipment/recommendation",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Рекомендации по эксплуатации оборудования",
        description="API для работы с рекомендациями по эксплуатации оборудования")
public class RecommendationSpecialistController {

    private final RecommendationSpecialistService service;

    @Operation(summary = "Новая рекомендация для раздела отчета")
    @PostMapping
    public ResponseEntity<ResponseRecommendationSpecialistDto> save(
            @RequestBody @Valid @Parameter(name = "Рекомендация") NewRecommendationSpecialistDto recommendationDto) {
        return ResponseEntity.ok().body(service.save(recommendationDto));
    }

    @Operation(summary = "Изменение рекомендации")
    @PatchMapping
    public ResponseEntity<ResponseRecommendationSpecialistDto> update(
            @RequestBody @Valid @Parameter(name = "Рекомендация") UpdateRecommendationSpecialistDto recommendationDto) {
        return ResponseEntity.ok().body(service.update(recommendationDto));
    }

    @Operation(summary = "Получить рекомендации по типу объекта")
    @GetMapping
    public ResponseEntity<List<ResponseRecommendationSpecialistDto>> getAll(
                                            @RequestParam(name = "id") @NotNull @Positive
                                            @Parameter(description = "Идентификатор оборудования") Long equipmentId) {
        return ResponseEntity.ok().body(service.getAll(equipmentId));
    }

    @Operation(summary = "Удалить рекомендацию")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Идентификатор") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Рекомендация успешно удалена.");
    }
}