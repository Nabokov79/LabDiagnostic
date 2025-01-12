package ru.nabokovsg.equipment.mapper.equipment;

import org.mapstruct.Mapper;
import ru.nabokovsg.equipment.dto.recommendationSpecialist.NewRecommendationSpecialistDto;
import ru.nabokovsg.equipment.dto.recommendationSpecialist.ResponseRecommendationSpecialistDto;
import ru.nabokovsg.equipment.dto.recommendationSpecialist.UpdateRecommendationSpecialistDto;
import ru.nabokovsg.equipment.model.equipment.RecommendationSpecialist;

@Mapper(componentModel = "spring")
public interface RecommendationSpecialistMapper {

    RecommendationSpecialist mapToRecommendationSpecialist(NewRecommendationSpecialistDto recommendationDto);

    RecommendationSpecialist mapToUpdateRecommendationSpecialist(UpdateRecommendationSpecialistDto recommendationDto);

    ResponseRecommendationSpecialistDto mapToResponseRecommendationSpecialistDto(RecommendationSpecialist recommendation);
}