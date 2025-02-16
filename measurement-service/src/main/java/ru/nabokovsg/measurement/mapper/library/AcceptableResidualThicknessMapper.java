package ru.nabokovsg.measurement.mapper.library;

import org.mapstruct.Mapper;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.NewAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.ResponseAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.UpdateAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.model.library.AcceptableResidualThickness;

@Mapper(componentModel = "spring")
public interface AcceptableResidualThicknessMapper {

    AcceptableResidualThickness mapToAcceptableThickness(NewAcceptableResidualThicknessDto thicknessDto);

    AcceptableResidualThickness mapToUpdateAcceptableThickness(UpdateAcceptableResidualThicknessDto thicknessDto);

    ResponseAcceptableResidualThicknessDto mapToResponseAcceptableResidualThicknessDto(AcceptableResidualThickness thickness);
}