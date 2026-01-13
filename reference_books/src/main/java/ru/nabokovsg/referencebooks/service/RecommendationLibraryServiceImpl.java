package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.NewRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.ResponseRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.dto.recommendationLibrary.UpdateRecommendationLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.RecommendationLibraryMapper;
import ru.nabokovsg.referencebooks.repository.RecommendationLibraryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationLibraryServiceImpl implements RecommendationLibraryService {

    private final RecommendationLibraryRepository repository;
    private final RecommendationLibraryMapper mapper;

    @Override
    public ResponseRecommendationLibraryDto save(NewRecommendationLibraryDto recommendationLibraryDto) {
        if (repository.existsByEquipmentLibraryIdAndRecommendation(recommendationLibraryDto.getEquipmentLibraryId()
                , recommendationLibraryDto.getRecommendation())) {
            throw new BadRequestException("Обнаружен дубликат.");
        }
        return mapper.mapToResponseRecommendationLibraryDto(
                repository.save(mapper.mapToRecommendationLibrary(recommendationLibraryDto)));
    }

    @Override
    public ResponseRecommendationLibraryDto update(UpdateRecommendationLibraryDto recommendationLibraryDto) {
        if (repository.existsById(recommendationLibraryDto.getId())) {
            return mapper.mapToResponseRecommendationLibraryDto(
                    repository.save(mapper.mapToUpdateRecommendationLibrary(recommendationLibraryDto)));
        }
        throw new NotFoundException("Рекомендация не найдена.");
    }

    @Override
    public ResponseRecommendationLibraryDto get(Long id) {
        return mapper.mapToResponseRecommendationLibraryDto(
                repository.findById(id).orElseThrow(() -> new NotFoundException("Рекомендация не найдена.")));
    }

    @Override
    public List<ResponseRecommendationLibraryDto> getAll(Long equipmentLibraryId) {
        return repository.findAllByEquipmentLibraryId(equipmentLibraryId)
                .stream()
                .map(mapper::mapToResponseRecommendationLibraryDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException("Рекомендация не найдена.");
    }
}