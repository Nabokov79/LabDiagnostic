package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.NewRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.ResponseRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.UpdateRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.RecommendationLibraryMapper;
import ru.nabokovsg.referencebooks.model_enum.BadRequestExceptionMassage;
import ru.nabokovsg.referencebooks.model.RecommendationLibrary;
import ru.nabokovsg.referencebooks.model_enum.NotFoundExceptionMassage;
import ru.nabokovsg.referencebooks.repository.RecommendationLibraryRepository;
import ru.nabokovsg.referencebooks.search.SearchService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationLibraryServiceImpl implements RecommendationLibraryService {

    private final RecommendationLibraryRepository repository;
    private final RecommendationLibraryMapper mapper;
    private final EquipmentLibraryService equipmentService;
    private final SearchService searchService;

    @Override
    public ResponseRecommendationLibraryDto save(NewRecommendationLibraryDto recommendationLibraryDto) {
        RecommendationLibrary recommendation = mapper.mapToRecommendationLibrary(recommendationLibraryDto);
        build(recommendation, recommendationLibraryDto.getEquipmentId());
        return mapper.mapToResponseRecommendationLibraryDto(repository.save(recommendation));
    }

    @Override
    public ResponseRecommendationLibraryDto update(UpdateRecommendationLibraryDto recommendationLibraryDto) {
        RecommendationLibrary recommendation = getById(recommendationLibraryDto.getId());
        mapper.mapToUpdateRecommendationLibrary(recommendation, recommendationLibraryDto);
        build(recommendation, recommendationLibraryDto.getEquipmentId());
        return mapper.mapToResponseRecommendationLibraryDto(repository.save(recommendation));
    }

    @Override
    public ResponseRecommendationLibraryDto get(Long id) {
        return mapper.mapToResponseRecommendationLibraryDto(getById(id));
    }

    @Override
    public List<ResponseRecommendationLibraryDto> getAll(String search) {
        Set<RecommendationLibrary> recommendations = repository.findAllByOrderByEquipmentLibrary();
        if (search != null) {
            recommendations  = recommendations.stream()
                    .filter(recommendation ->
                            searchService.search(search, List.of(recommendation.getEquipment().getEquipmentFullName()
                                                               , recommendation.getRecommendation())))
                    .collect(Collectors.toSet());
        }
        return recommendations.stream()
                              .map(mapper::mapToResponseRecommendationLibraryDto)
                              .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(NotFoundExceptionMassage.RECOMMENDATION.label);
    }

    private RecommendationLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundExceptionMassage.RECOMMENDATION.label));
    }

    private void build(RecommendationLibrary recommendation, Long equipmentId) {
        exists(recommendation, equipmentId);
        mapper.mapWithFields(recommendation, equipmentService.getById(equipmentId));
    }

    private void exists(RecommendationLibrary recommendation, Long equipmentId) {
        if (repository.existsByEquipmentIdAndRecommendation(equipmentId, recommendation.getRecommendation())) {
            throw new BadRequestException(String.join("", BadRequestExceptionMassage.DUPLICATE.label
                                                                , recommendation.getRecommendation()));
        }
    }
}