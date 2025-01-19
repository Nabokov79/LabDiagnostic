package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.measuredParameter.UpdateMeasuredParameterDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.mapper.diagnostics.MeasuredParameterMapper;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.ParameterMeasurementBuilder;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;
import ru.nabokovsg.measurement.repository.diagnostics.MeasuredParameterRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeasuredParameterServiceImpl implements MeasuredParameterService {

    private final MeasuredParameterRepository repository;
    private final MeasuredParameterMapper mapper;

    @Override
    public Set<MeasuredParameter> save(ParameterMeasurementBuilder builder) {
        return new HashSet<>(repository.saveAll(build(builder)));
    }

    @Override
    public Set<MeasuredParameter> update(Set<MeasuredParameter> measuredParameters
                                       , List<UpdateMeasuredParameterDto> measuredParametersDto) {
        if (measuredParametersDto != null) {
            Map<Long, Double> values = measuredParametersDto.stream()
                    .collect(Collectors.toMap(UpdateMeasuredParameterDto::getId, UpdateMeasuredParameterDto::getValue));
            measuredParameters.forEach(parameter -> mapper.mapToUpdateMeasuredParameter(parameter, values.get(parameter.getId())));
        }
        return new HashSet<>(repository.saveAll(measuredParameters));
    }

    @Override
    public void deleteAll(Set<MeasuredParameter> measuredParameters) {
        List<Long> ids = measuredParameters.stream().map(MeasuredParameter::getId).toList();
        repository.deleteAllById(ids);
    }

    private List<MeasuredParameter> build(ParameterMeasurementBuilder builder) {
        Map<Long, MeasurementParameterLibrary> measuredParametersLibraries = builder.getMeasurementParameterLibraries()
                .stream()
                .collect(Collectors.toMap(MeasurementParameterLibrary::getId, parameter -> parameter));
        switch (builder.getLibraryDataType()) {
            case REPAIR -> {
                return builder.getNewMeasuredParameters()
                        .stream()
                        .map(parameter -> mapper.mapWithRepair(
                                                      measuredParametersLibraries.get(parameter.getParameterLibraryId())
                                                    , parameter.getValue()
                                                    , builder.getRepair()))
                        .toList();
            }
            case DEFECT -> {
                return builder.getNewMeasuredParameters()
                              .stream()
                              .map(parameter -> mapper.mapWithDefect(
                                                      measuredParametersLibraries.get(parameter.getParameterLibraryId())
                                                    , parameter.getValue()
                                                    , builder.getDefect()))
                              .toList();
            }
            case WELD_DEFECT -> {
                builder.getMeasuredParameters()
                        .forEach(parameter -> mapper.mapWithWeldDefect(parameter, builder.getWeldDefect()));
                return new ArrayList<>(builder.getMeasuredParameters());
            }
            default -> throw new BadRequestException(
                    String.format("Parameter mapping is not supported, type=%s", builder.getLibraryDataType()));
        }
    }
}