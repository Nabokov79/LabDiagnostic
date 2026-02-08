package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.NewRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.ResponseRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.UpdateRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.RecommendationLibraryMapper;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.model.RecommendationLibrary;
import ru.nabokovsg.referencebooks.repository.RecommendationLibraryRepository;
import ru.nabokovsg.referencebooks.toStringService.ToStringService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationLibraryServiceImpl implements RecommendationLibraryService {

    private final RecommendationLibraryRepository repository;
    private final RecommendationLibraryMapper mapper;
    private final EquipmentLibraryService equipmentService;
    private final ToStringService toString;
    private final static String MASSAGE = "Рекомендация не найдена.";

    @Override
    public ResponseRecommendationLibraryDto save(NewRecommendationLibraryDto recommendationLibraryDto) {
        RecommendationLibrary recommendation = mapper.mapToRecommendationLibrary(recommendationLibraryDto);
        build(recommendation);
        return mapper.mapToResponseRecommendationLibraryDto(repository.save(recommendation));
    }

    @Override
    public ResponseRecommendationLibraryDto update(UpdateRecommendationLibraryDto recommendationLibraryDto) {
        RecommendationLibrary recommendation = getById(recommendationLibraryDto.getId());
        mapper.mapToUpdateRecommendationLibrary(recommendation, recommendationLibraryDto);
        build(recommendation);
        return mapper.mapToResponseRecommendationLibraryDto(repository.save(recommendation));
    }

    @Override
    public ResponseRecommendationLibraryDto get(Long id) {
        return mapper.mapToResponseRecommendationLibraryDto(getById(id));
    }

    @Override
    public List<ResponseRecommendationLibraryDto> getAll(String name) {
        Set<RecommendationLibrary> recommendations = repository.findAllByOrderByEquipmentLibrary();
        if (name != null) {
            final String search = name.toLowerCase();
            recommendations  = recommendations.stream()
                    .filter(hardness -> hardness.getEquipmentLibrary().toLowerCase().contains(search)
                                     || hardness.getRecommendation().toLowerCase().contains(search))
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
        throw new NotFoundException(MASSAGE);
    }

    private RecommendationLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(MASSAGE));
    }

    private void build(RecommendationLibrary recommendation) {
        exists(recommendation);
        mapper.mapWithFields(recommendation
               , toString.getEquipmentLibraryFullName(equipmentService.getById(recommendation.getEquipmentLibraryId())));
    }

    private void exists(RecommendationLibrary recommendation) {
        if (repository.existsByEquipmentLibraryIdAndRecommendation(recommendation.getEquipmentLibraryId()
                , recommendation.getRecommendation())) {
            throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label
                                                                , recommendation.getRecommendation()));
        }
    }
}