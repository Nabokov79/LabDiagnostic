package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.dto.measuredParameter.UpdateMeasuredParameterDto;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.ParameterMeasurementBuilder;

import java.util.List;
import java.util.Set;

public interface MeasuredParameterService {

    Set<MeasuredParameter> save(ParameterMeasurementBuilder builder);

    Set<MeasuredParameter> update(Set<MeasuredParameter> measuredParameters
                                , List<UpdateMeasuredParameterDto> measuredParametersDto);

    void deleteAll(Set<MeasuredParameter> measuredParameters);
}