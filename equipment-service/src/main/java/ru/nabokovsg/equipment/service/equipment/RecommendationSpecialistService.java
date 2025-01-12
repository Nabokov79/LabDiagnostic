package ru.nabokovsg.equipment.service.equipment;

import ru.nabokovsg.equipment.dto.recommendationSpecialist.NewRecommendationSpecialistDto;
import ru.nabokovsg.equipment.dto.recommendationSpecialist.ResponseRecommendationSpecialistDto;
import ru.nabokovsg.equipment.dto.recommendationSpecialist.UpdateRecommendationSpecialistDto;

import java.util.List;

public interface RecommendationSpecialistService {

    ResponseRecommendationSpecialistDto save(NewRecommendationSpecialistDto recommendationDto);

    ResponseRecommendationSpecialistDto update(UpdateRecommendationSpecialistDto recommendationDto);

    List<ResponseRecommendationSpecialistDto> getAll(Long equipmentId);

    void delete(Long id);
}