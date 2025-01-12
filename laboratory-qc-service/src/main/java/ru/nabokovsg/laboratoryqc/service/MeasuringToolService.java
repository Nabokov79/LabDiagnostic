package ru.nabokovsg.laboratoryqc.service;

import ru.nabokovsg.laboratoryqc.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.laboratoryqc.dto.measuringTool.ResponseMeasuringToolDto;
import ru.nabokovsg.laboratoryqc.dto.measuringTool.UpdateMeasuringToolDto;

import java.time.LocalDate;
import java.util.List;

public interface MeasuringToolService {

    ResponseMeasuringToolDto save(NewMeasuringToolDto measuringToolDto);

    ResponseMeasuringToolDto update(UpdateMeasuringToolDto measuringToolDto);

    ResponseMeasuringToolDto get(Long id);

    List<ResponseMeasuringToolDto> getAll(String search, LocalDate exploitation, Long employeeId);

    void delete(Long id);
}