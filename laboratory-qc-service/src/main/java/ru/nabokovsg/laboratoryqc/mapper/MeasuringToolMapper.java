package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryqc.dto.measuringTool.NewMeasuringToolDto;
import ru.nabokovsg.laboratoryqc.dto.measuringTool.ResponseMeasuringToolDto;
import ru.nabokovsg.laboratoryqc.dto.measuringTool.UpdateMeasuringToolDto;
import ru.nabokovsg.laboratoryqc.model.MeasuringTool;

@Mapper(componentModel = "spring")
public interface MeasuringToolMapper {

    MeasuringTool mapToMeasuringTool(NewMeasuringToolDto measuringToolDto);

    MeasuringTool mapToUpdateMeasuringTool(UpdateMeasuringToolDto measuringToolDto);

    ResponseMeasuringToolDto mapToResponseMeasuringToolDto(MeasuringTool newMeasuringTool);
}