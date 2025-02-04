package ru.nabokovsg.measurement.service.library;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.dto.measurementParameterLibrary.NewMeasurementParameterLibraryDto;
import ru.nabokovsg.measurement.dto.measurementParameterLibrary.UpdateMeasurementParameterLibraryDto;
import ru.nabokovsg.measurement.exceptions.BadRequestException;
import ru.nabokovsg.measurement.mapper.library.MeasuredParameterLibraryMapper;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;
import ru.nabokovsg.measurement.model.common.MeasurementParameterType;
import ru.nabokovsg.measurement.model.library.TypeMeasuredParameterBuilder;
import ru.nabokovsg.measurement.model.library.UnitMeasurementType;
import ru.nabokovsg.measurement.repository.library.MeasuredParameterLibraryRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasuredParameterLibraryServiceImpl implements MeasuredParameterLibraryService {

    private final MeasuredParameterLibraryRepository repository;
    private final MeasuredParameterLibraryMapper mapper;

    @Override
    public Set<MeasurementParameterLibrary> save(TypeMeasuredParameterBuilder builder
            , List<NewMeasurementParameterLibraryDto> measuredParametersDto) {
        List<MeasurementParameterLibrary> parametersLibrary = measuredParametersDto.stream()
                .map(parameter -> mapper.mapToMeasuredParameter(
                                                      MeasurementParameterType.valueOf(parameter.getParameterName()).label
                                                    , UnitMeasurementType.valueOf(parameter.getUnitMeasurement()).label)
                )
                .toList();
        parametersLibrary.forEach(parameter -> map(builder, parameter));
        return new HashSet<>(repository.saveAll(parametersLibrary));
    }

    @Override
    public Set<MeasurementParameterLibrary> update(TypeMeasuredParameterBuilder builder
                                                 , List<UpdateMeasurementParameterLibraryDto> measuredParametersDto) {
        Map<Long, UpdateMeasurementParameterLibraryDto> parametersLibraryDto =
                measuredParametersDto.stream()
                                     .collect(Collectors.toMap(UpdateMeasurementParameterLibraryDto::getId
                                                             , parameter -> parameter));
        builder.getMeasuredParameters().forEach(parameter -> {
            UpdateMeasurementParameterLibraryDto parameterDto = parametersLibraryDto.get(parameter.getId());
            mapper.mapToUpdateMeasuredParameter(parameter
                                              , MeasurementParameterType.valueOf(parameterDto.getParameterName()).label
                                              , UnitMeasurementType.valueOf(parameterDto.getUnitMeasurement()).label);
            map(builder, parameter);
        });
        repository.saveAll(builder.getMeasuredParameters());
        return builder.getMeasuredParameters();
    }

    private void map(TypeMeasuredParameterBuilder builder, MeasurementParameterLibrary parameter) {
        switch (builder.getLibraryDataType()) {
            case DEFECT -> mapper.mapWithDefect(parameter, builder.getDefect());
            case REPAIR -> mapper.mapWithRepair(parameter, builder.getRepair());
            default -> throw new BadRequestException(
                    String.format("Incorrect library data type =%s", builder.getLibraryDataType()));
        }
    }
}