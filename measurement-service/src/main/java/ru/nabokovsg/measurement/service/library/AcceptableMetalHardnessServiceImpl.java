package ru.nabokovsg.measurement.service.library;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.acceptableMetalHardness.NewAcceptableMetalHardnessDto;
import ru.nabokovsg.measurement.dto.acceptableMetalHardness.ResponseAcceptableMetalHardnessDto;
import ru.nabokovsg.measurement.dto.acceptableMetalHardness.UpdateAcceptableMetalHardnessDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.library.AcceptableMetalHardnessMapper;
import ru.nabokovsg.measurement.model.library.AcceptableMetalHardness;
import ru.nabokovsg.measurement.model.library.QAcceptableMetalHardness;
import ru.nabokovsg.measurement.repository.library.AcceptableMetalHardnessRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AcceptableMetalHardnessServiceImpl implements AcceptableMetalHardnessService {

    private final AcceptableMetalHardnessRepository repository;
    private final AcceptableMetalHardnessMapper mapper;
    private final EntityManager em;

    @Override
    public ResponseAcceptableMetalHardnessDto save(NewAcceptableMetalHardnessDto hardnessDto) {
        validateAcceptableStandards(hardnessDto.getMinAcceptableDiameter(), hardnessDto.getMinAcceptableThickness());
        return mapper.mapToResponseAcceptableMetalHardnessDto(
                repository.save(searchDuplicate(mapper.mapToAcceptableHardness(hardnessDto))));
    }

    @Override
    public ResponseAcceptableMetalHardnessDto update(UpdateAcceptableMetalHardnessDto hardnessDto) {
        validateAcceptableStandards(hardnessDto.getMinAcceptableDiameter(), hardnessDto.getMinAcceptableThickness());
        if (repository.existsById(hardnessDto.getId())) {
            return mapper.mapToResponseAcceptableMetalHardnessDto(
                    repository.save(searchDuplicate(mapper.mapToUpdateAcceptableHardness(hardnessDto))));
        }
        throw new NotFoundException(
                String.format("AcceptableHardness with id=%s not found for update", hardnessDto.getId())
        );
    }

    @Override
    public ResponseAcceptableMetalHardnessDto get(Long id) {
        return mapper.mapToResponseAcceptableMetalHardnessDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("AcceptableHardness with id=%s not found", id))));
    }

    @Override
    public List<ResponseAcceptableMetalHardnessDto> getAll(Long equipmentLibraryId) {
        return repository.findAllByEquipmentLibraryId(equipmentLibraryId)
                .stream()
                .map(mapper::mapToResponseAcceptableMetalHardnessDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("AcceptableHardness with id=%s not found for delete", id));
    }

    private AcceptableMetalHardness searchDuplicate(AcceptableMetalHardness acceptableMetalHardness) {
        QAcceptableMetalHardness metalHardness = QAcceptableMetalHardness.acceptableMetalHardness;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(metalHardness.equipmentLibraryId.eq(acceptableMetalHardness.getEquipmentLibraryId()));
        builder.and(metalHardness.elementLibraryId.eq(acceptableMetalHardness.getElementLibraryId()));
        if (acceptableMetalHardness.getPartElementLibraryId() != null) {
            builder.and(metalHardness.partElementLibraryId.eq(acceptableMetalHardness.getPartElementLibraryId()));
        }
        if (acceptableMetalHardness.getMinAcceptableThickness() != null) {
            builder.and(metalHardness.minAcceptableThickness.eq(acceptableMetalHardness.getMinAcceptableThickness()));
        }
        if (acceptableMetalHardness.getMinAcceptableDiameter() != null) {
            builder.and(metalHardness.minAcceptableDiameter.eq(acceptableMetalHardness.getMinAcceptableDiameter()));
        }
        AcceptableMetalHardness duplicate = new JPAQueryFactory(em).select(metalHardness)
                .from(metalHardness)
                .where(builder)
                .fetchOne();
        if (duplicate != null && (acceptableMetalHardness.getId() == null
                                            || !Objects.equals(duplicate.getId(), acceptableMetalHardness.getId()))) {
            throw new BadRequestException(
                    String.format("AcceptableMetalHardness duplicate=%s is found", acceptableMetalHardness));
        } else {
            return acceptableMetalHardness;
        }
    }

    private void validateAcceptableStandards(Integer minAcceptableDiameter, Double minAcceptableThickness) {
        if (minAcceptableDiameter == null &&  minAcceptableThickness == null) {
            throw new BadRequestException(
                    String.format("There are no min acceptable: minAcceptableDiameter=%s, minAcceptableThickness=%s"
                            , minAcceptableDiameter, minAcceptableThickness));
        }
        if (minAcceptableDiameter != null && minAcceptableDiameter <= 0) {
            throw new BadRequestException(
                    String.format("minAcceptableDiameter can only be positive: %s", minAcceptableDiameter));
        }
        if (minAcceptableThickness != null && minAcceptableThickness <= 0) {
            throw new BadRequestException(
                    String.format("minAcceptableThickness can only be positive: %s", minAcceptableThickness));
        }
    }
}