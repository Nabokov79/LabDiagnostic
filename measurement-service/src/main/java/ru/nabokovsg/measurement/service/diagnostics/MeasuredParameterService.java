package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.dto.measuredParameter.NewMeasuredParameterDto;
import ru.nabokovsg.measurement.dto.measuredParameter.UpdateMeasuredParameterDto;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.ParameterMeasurementBuilder;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MeasuredParameterService {

    Set<MeasuredParameter> save(ParameterMeasurementBuilder builder);

    Set<MeasuredParameter> update(Set<MeasuredParameter> measuredParameters
                                , List<UpdateMeasuredParameterDto> measuredParametersDto);

    void updateDuplicate(Set<MeasuredParameter> measuredParameters);

    void deleteAll(Set<MeasuredParameter> measuredParameters);

    boolean compare(Set<MeasuredParameter> measuredParameters, Map<Long, Double> parameters);

    Set<MeasuredParameter> create(List<NewMeasuredParameterDto> parameters
                                , Set<MeasurementParameterLibrary> parametersLibrary);
}