package ru.nabokovsg.measurement.service.library;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.NewAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.ResponseAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.UpdateAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.exceptions.NotFoundException;
import ru.nabokovsg.measurement.mapper.library.AcceptableResidualThicknessMapper;
import ru.nabokovsg.measurement.model.library.AcceptableResidualThickness;
import ru.nabokovsg.measurement.model.library.QAcceptableResidualThickness;
import ru.nabokovsg.measurement.repository.library.AcceptableResidualThicknessRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AcceptableResidualThicknessServiceImpl implements AcceptableResidualThicknessService {

    private final AcceptableResidualThicknessRepository repository;
    private final AcceptableResidualThicknessMapper mapper;
    private final EntityManager em;

    @Override
    public ResponseAcceptableResidualThicknessDto save(NewAcceptableResidualThicknessDto thicknessDto) {
        validateAcceptableStandards(thicknessDto.getAcceptableThickness(), thicknessDto.getAcceptablePercent());
        return mapper.mapToResponseAcceptableResidualThicknessDto(
                repository.save(searchDuplicate(mapper.mapToAcceptableThickness(thicknessDto))));
    }

    @Override
    public ResponseAcceptableResidualThicknessDto update(UpdateAcceptableResidualThicknessDto thicknessDto) {
        validateAcceptableStandards(thicknessDto.getAcceptableThickness(), thicknessDto.getAcceptablePercent());
        if (repository.existsById(thicknessDto.getId())) {
            return mapper.mapToResponseAcceptableResidualThicknessDto(
                        repository.save(searchDuplicate(mapper.mapToUpdateAcceptableThickness(thicknessDto))));
        }
        throw new NotFoundException(
                String.format("AcceptableResidualThickness with id=%s not found for update", thicknessDto.getId())
        );
    }

    @Override
    public ResponseAcceptableResidualThicknessDto get(Long id) {
        return mapper.mapToResponseAcceptableResidualThicknessDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("AcceptableThickness with id=%s not found", id))));
    }

    @Override
    public List<ResponseAcceptableResidualThicknessDto> getAll(Long equipmentLibraryId) {
        return repository.findAllByEquipmentLibraryId(equipmentLibraryId)
                .stream()
                .map(mapper::mapToResponseAcceptableResidualThicknessDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("AcceptableThickness with id=%s not found for delete", id));
    }

    private AcceptableResidualThickness searchDuplicate(AcceptableResidualThickness acceptableThickness) {
        QAcceptableResidualThickness thickness = QAcceptableResidualThickness.acceptableResidualThickness;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(thickness.equipmentLibraryId.eq(acceptableThickness.getEquipmentLibraryId()));
        builder.and(thickness.elementLibraryId.eq(acceptableThickness.getElementLibraryId()));
        if (acceptableThickness.getPartElementLibraryId() != null) {
            builder.and(thickness.partElementLibraryId.eq(acceptableThickness.getPartElementLibraryId()));
        }
        if (acceptableThickness.getThickness() != null) {
            builder.and(thickness.thickness.eq(acceptableThickness.getThickness()));
        }
        if (acceptableThickness.getDiameter() != null) {
            builder.and(thickness.diameter.eq(acceptableThickness.getDiameter()));
        }
        AcceptableResidualThickness duplicate = new JPAQueryFactory(em).select(thickness)
                                                                       .from(thickness)
                                                                       .where(builder)
                                                                       .fetchOne();
        if (duplicate != null && (acceptableThickness.getId() == null || !Objects.equals(duplicate.getId(), acceptableThickness.getId()))) {
            throw new BadRequestException(
                    String.format("AcceptableResidualThickness duplicate=%s is found", acceptableThickness));
        } else {
            return acceptableThickness;
        }
    }

    private void validateAcceptableStandards(Double acceptableThickness, Integer acceptablePercent) {
        if (acceptableThickness == null &&  acceptablePercent == null) {
            throw new BadRequestException(
                    String.format("There are no standards: acceptableThickness=%s, acceptablePercent=%s"
                                                                , acceptableThickness, acceptablePercent));
        }
        if (acceptableThickness != null && acceptableThickness <= 0) {
            throw new BadRequestException(
                    String.format("acceptableThickness can only be positive: %s", acceptableThickness));
        }
        if (acceptablePercent != null && acceptablePercent <= 0) {
            throw new BadRequestException(
                    String.format("acceptablePercent can only be positive: %s", acceptablePercent));
        }
    }
}