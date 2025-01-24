package ru.nabokovsg.measurement.service.diagnostics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.measuredParameter.NewMeasuredParameterDto;
import ru.nabokovsg.measurement.dto.measuredParameter.UpdateMeasuredParameterDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.mapper.diagnostics.MeasuredParameterMapper;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.ParameterMeasurementBuilder;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;
import ru.nabokovsg.measurement.repository.diagnostics.MeasuredParameterRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasuredParameterServiceImpl implements MeasuredParameterService {

    private final MeasuredParameterRepository repository;
    private final MeasuredParameterMapper mapper;

    @Override
    public Set<MeasuredParameter> save(ParameterMeasurementBuilder builder) {
        build(builder);
        return new HashSet<>(repository.saveAll(builder.getMeasuredParameters()));
    }

    @Override
    public Set<MeasuredParameter> update(Set<MeasuredParameter> measuredParameters
                                       , List<UpdateMeasuredParameterDto> measuredParametersDto) {
        Map<Long, Double> values = measuredParametersDto.stream()
                .collect(Collectors.toMap(UpdateMeasuredParameterDto::getId, UpdateMeasuredParameterDto::getValue));
        measuredParameters.forEach(parameter -> mapper.mapToUpdateMeasuredParameter(parameter, values.get(parameter.getId())));
        return new HashSet<>(repository.saveAll(measuredParameters));
    }

    @Override
    public void updateDuplicate(Set<MeasuredParameter> measuredParameters) {
        repository.saveAll(measuredParameters);
    }

    @Override
    public void deleteAll(Set<MeasuredParameter> measuredParameters) {
        List<Long> ids = measuredParameters.stream().map(MeasuredParameter::getId).toList();
        repository.deleteAllById(ids);
    }

    @Override
    public boolean compare(Set<MeasuredParameter> measuredParameters, Map<Long, Double> parameters) {
        int coincidences = 0;
        String quantityName = MeasurementParameterType.QUANTITY.label;
        for (MeasuredParameter parameterDb : measuredParameters) {
            Double value = parameters.get(parameterDb.getParameterId());
            if (parameterDb.getParameterName().equals(quantityName)) {
                coincidences++;
            } else if (parameterDb.getValue().equals(value)) {
                coincidences++;
            }
        }
        return coincidences == parameters.size();
    }

    @Override
    public Set<MeasuredParameter> create(List<NewMeasuredParameterDto> parameters, Set<MeasurementParameterLibrary> parametersLibrary) {
        Map<Long, MeasurementParameterLibrary> measuredParametersLibraries = parametersLibrary
                .stream()
                .collect(Collectors.toMap(MeasurementParameterLibrary::getId, parameter -> parameter));
        return parameters.stream()
                .map(parameter -> mapper.mapToMeasuredParameter(measuredParametersLibraries.get(parameter.getParameterLibraryId()), parameter.getValue()))
                .collect(Collectors.toSet());
    }

    private void build(ParameterMeasurementBuilder builder) {
        switch (builder.getLibraryDataType()) {
            case REPAIR ->
                 builder.getMeasuredParameters()
                         .forEach(parameter -> mapper.mapWithRepair(parameter, builder.getRepair()));
            case DEFECT ->
                 builder.getMeasuredParameters()
                              .forEach(parameter -> mapper.mapWithDefect(parameter, builder.getDefect()));
            case WELD_DEFECT ->
                builder.getMeasuredParameters()
                        .forEach(parameter -> mapper.mapWithWeldDefect(parameter, builder.getWeldDefect()));
            default -> throw new BadRequestException(
                    String.format("Parameter mapping is not supported, type=%s", builder.getLibraryDataType()));
        }
    }
}