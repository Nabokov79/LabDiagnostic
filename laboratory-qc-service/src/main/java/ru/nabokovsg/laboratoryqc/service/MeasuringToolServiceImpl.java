package ru.nabokovsg.laboratoryqc.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.laboratoryqc.dto.measuringTool.ResponseMeasuringToolDto;
import ru.nabokovsg.laboratoryqc.dto.measuringTool.UpdateMeasuringToolDto;
import ru.nabokovsg.laboratoryqc.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryqc.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryqc.mapper.MeasuringToolMapper;
import ru.nabokovsg.laboratoryqc.model.MeasuringTool;
import ru.nabokovsg.laboratoryqc.model.QMeasuringTool;
import ru.nabokovsg.laboratoryqc.repository.MeasuringToolRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasuringToolServiceImpl implements MeasuringToolService {

    private final MeasuringToolRepository repository;
    private final MeasuringToolMapper mapper;
    private final EntityManager em;

    @Override
    public ResponseMeasuringToolDto save(NewMeasuringToolDto measuringToolDto) {
        if (getDuplicate(measuringToolDto) == null) {
            return  mapper.mapToResponseMeasuringToolDto(
                    repository.save(mapper.mapToMeasuringTool(measuringToolDto)));
        }
        throw new BadRequestException(String.format("MeasuringTool is found : %s", measuringToolDto));
    }

    @Override
    public ResponseMeasuringToolDto update(UpdateMeasuringToolDto measuringToolDto) {
        if (repository.existsById(measuringToolDto.getId())) {
            return mapper.mapToResponseMeasuringToolDto(
                    repository.save(mapper.mapToUpdateMeasuringTool(measuringToolDto)));
        }
        throw new NotFoundException(
                String.format("MeasuringTool with id=%s not found for update", measuringToolDto.getId())
        );
    }

    @Override
    public ResponseMeasuringToolDto get(Long id) {
        return mapper.mapToResponseMeasuringToolDto(
                            repository.findById(id)
                                    .orElseThrow(() -> new NotFoundException(
                                            String.format("measuring tool with id = %s not found", id))));
    }

    @Override
    public List<ResponseMeasuringToolDto> getAll(String search, LocalDate exploitation, Long employeeId) {
        QMeasuringTool measuringTool = QMeasuringTool.measuringTool;
        BooleanBuilder builder = new BooleanBuilder();
        if (exploitation != null) {
            builder.and(measuringTool.exploitation.in(exploitation));
        }
        if (employeeId != null) {
            builder.and(measuringTool.employeeId.in(employeeId));
        }
        List<MeasuringTool> measuringTools = new JPAQueryFactory(em).from(measuringTool)
                                                                    .select(measuringTool)
                                                                    .where(builder)
                                                                    .fetch();
        if (search != null) {
            measuringTools.forEach(tool -> {
                if (!filter(tool, search)) {
                    measuringTools.remove(tool);
                }
            });
        }
        return measuringTools.stream()
                             .map(mapper::mapToResponseMeasuringToolDto)
                             .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("measuring tool with id = %s not found for delete", id));
    }

    private boolean filter(MeasuringTool measuringTool, String search) {
        if (search.contains(measuringTool.getTollName())) {
            return true;
        }
        if (search.contains(measuringTool.getModel())) {
            return true;
        }
        if (search.contains(measuringTool.getManufacturer())) {
            return true;
        }
        if (search.contains(measuringTool.getOrganization())) {
            return true;
        }
        return search.contains(measuringTool.getControlName());
    }

    private MeasuringTool getDuplicate(NewMeasuringToolDto measuringToolDto) {
        QMeasuringTool measuringTool = QMeasuringTool.measuringTool;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(measuringTool.tollName.in(measuringToolDto.getTollName()));
        if (measuringToolDto.getModel() != null) {
            builder.and(measuringTool.model.in(measuringToolDto.getModel()));
        }
        builder.and(measuringTool.workNumber.eq(measuringToolDto.getWorkNumber()));
        builder.and(measuringTool.manufacturing.eq(measuringToolDto.getManufacturing()));
        builder.and(measuringTool.exploitation.eq(measuringToolDto.getExploitation()));
        builder.and(measuringTool.manufacturer.eq(measuringToolDto.getManufacturer()));
        return new JPAQueryFactory(em).from(measuringTool)
                                      .select(measuringTool)
                                      .where(builder)
                                      .fetchOne();
    }
}