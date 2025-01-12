package ru.nabokovsg.equipment.service.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.recommendationSpecialist.NewRecommendationSpecialistDto;
import ru.nabokovsg.equipment.dto.recommendationSpecialist.ResponseRecommendationSpecialistDto;
import ru.nabokovsg.equipment.dto.recommendationSpecialist.UpdateRecommendationSpecialistDto;
import ru.nabokovsg.equipment.exceptions.BadRequestException;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mapper.equipment.RecommendationSpecialistMapper;
import ru.nabokovsg.equipment.repository.equipment.RecommendationSpecialistRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationSpecialistServiceImpl implements RecommendationSpecialistService {

    private final RecommendationSpecialistRepository repository;
    private final RecommendationSpecialistMapper mapper;

    @Override
    public ResponseRecommendationSpecialistDto save(NewRecommendationSpecialistDto recommendationDto) {
        if (repository.existsByEquipmentIdAndRecommendation(recommendationDto.getEquipmentId()
                , recommendationDto.getRecommendation())) {
            throw new BadRequestException(
                    String.format("RecommendationSpecialist recommendation=%s is found"
                            , recommendationDto.getRecommendation()));
        }
        return mapper.mapToResponseRecommendationSpecialistDto(
                repository.save(mapper.mapToRecommendationSpecialist(recommendationDto)));
    }

    @Override
    public ResponseRecommendationSpecialistDto update(UpdateRecommendationSpecialistDto recommendationDto) {
        if (repository.existsById(recommendationDto.getId())) {
            return mapper.mapToResponseRecommendationSpecialistDto(
                    repository.save(mapper.mapToUpdateRecommendationSpecialist(recommendationDto)));
        }
        throw new NotFoundException(
                String.format("RecommendationSpecialist with id=%s not found for update", recommendationDto.getId()));
    }

    @Override
    public List<ResponseRecommendationSpecialistDto> getAll(Long equipmentId) {
        return repository.findAllByEquipmentId(equipmentId)
                .stream()
                .map(mapper::mapToResponseRecommendationSpecialistDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("RecommendationSpecialist with id=%s not found for delete", id));
    }
}