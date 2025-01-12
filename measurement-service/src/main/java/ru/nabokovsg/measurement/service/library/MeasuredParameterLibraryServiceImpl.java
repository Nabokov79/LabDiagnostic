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
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MeasuredParameterLibraryServiceImpl implements MeasuredParameterLibraryService {

    private final MeasuredParameterLibraryRepository repository;
    private final MeasuredParameterLibraryMapper mapper;

    @Override
    public Set<MeasurementParameterLibrary> save(TypeMeasuredParameterBuilder builder
            , List<NewMeasurementParameterLibraryDto> measuredParametersDto) {
        List<MeasurementParameterLibrary> parametersLibrary = measuredParametersDto.stream()
                .map(parameter -> {
                    parameter.setParameterName(MeasurementParameterType.valueOf(parameter.getParameterName()).label);
                    parameter.setUnitMeasurement(UnitMeasurementType.valueOf(parameter.getUnitMeasurement()).label);
                    return mapper.mapToMeasuredParameter(parameter);
                })
                .toList();
        parametersLibrary.forEach(parameter -> map(builder, parameter));
        return new HashSet<>(repository.saveAll(parametersLibrary));
    }

    @Override
    public Set<MeasurementParameterLibrary> update(Set<MeasurementParameterLibrary> measuredParametersDb
            , List<UpdateMeasurementParameterLibraryDto> measuredParametersDto) {
        return new HashSet<>(repository.saveAll(measuredParametersDto.stream()
                .map(parameter -> {
                    parameter.setParameterName(MeasurementParameterType.valueOf(parameter.getParameterName()).label);
                    parameter.setUnitMeasurement(UnitMeasurementType.valueOf(parameter.getUnitMeasurement()).label);
                    return mapper.mapToUpdateMeasuredParameter(parameter);
                })
                .toList()));
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