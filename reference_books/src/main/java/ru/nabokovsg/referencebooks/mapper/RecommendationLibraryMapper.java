package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.NewRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.ResponseRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.UpdateRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.model.RecommendationLibrary;

@Mapper(componentModel = "spring")
public interface RecommendationLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibrary", ignore = true)
    RecommendationLibrary mapToRecommendationLibrary(NewRecommendationLibraryDto recommendationLibraryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibrary", ignore = true)
    void mapToUpdateRecommendationLibrary(@MappingTarget RecommendationLibrary recommendationLibrary, UpdateRecommendationLibraryDto recommendationLibraryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "recommendation", ignore = true)
    void mapWithFields(@MappingTarget RecommendationLibrary recommendationLibrary, String equipmentLibrary);

    ResponseRecommendationLibraryDto mapToResponseRecommendationLibraryDto(RecommendationLibrary recommendationLibrary);
}