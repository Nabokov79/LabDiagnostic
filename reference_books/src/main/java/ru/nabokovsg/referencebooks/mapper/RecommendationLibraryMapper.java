package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.NewRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.ResponseRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.UpdateRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.model.RecommendationLibrary;

@Mapper(componentModel = "spring")
public interface RecommendationLibraryMapper {

    @Mapping(target = "id", ignore = true)
    RecommendationLibrary mapToRecommendationLibrary(NewRecommendationLibraryDto recommendationLibraryDto);

    RecommendationLibrary mapToUpdateRecommendationLibrary(UpdateRecommendationLibraryDto recommendationLibraryDto);

    ResponseRecommendationLibraryDto mapToResponseRecommendationLibraryDto(RecommendationLibrary recommendationLibrary);
}