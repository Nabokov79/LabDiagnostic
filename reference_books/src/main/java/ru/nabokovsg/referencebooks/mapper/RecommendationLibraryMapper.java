package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.NewRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.ResponseRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.UpdateRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;
import ru.nabokovsg.referencebooks.model.RecommendationLibrary;

@Mapper(componentModel = "spring")
public interface RecommendationLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    RecommendationLibrary mapToRecommendationLibrary(NewRecommendationLibraryDto recommendationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    void mapToUpdateRecommendationLibrary(@MappingTarget RecommendationLibrary recommendation, UpdateRecommendationLibraryDto recommendationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recommendation", ignore = true)
    void mapWithFields(@MappingTarget RecommendationLibrary recommendation, EquipmentLibrary equipment);

    @Mapping(source = "equipment.id", target = "equipmentId")
    @Mapping(source = "equipment.equipmentFullName", target = "equipmentFullName")
    @Mapping(source = "recommendation.id", target = "id")
    @Mapping(source = "recommendation.recommendation", target = "recommendation")
    ResponseRecommendationLibraryDto mapToResponseRecommendationLibraryDto(RecommendationLibrary recommendation);
}