package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.recommendationLibrary.NewRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.ResponseRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.UpdateRecommendationLibraryDto;

import java.util.List;

public interface RecommendationLibraryService {

    ResponseRecommendationLibraryDto save(NewRecommendationLibraryDto recommendationLibraryDto);

    ResponseRecommendationLibraryDto update(UpdateRecommendationLibraryDto recommendationLibraryDto);

    ResponseRecommendationLibraryDto get(Long id);

    List<ResponseRecommendationLibraryDto> getAll(String name);

    void delete(Long id);
}