package ru.nabokovsg.measurement.service.library;

import ru.nabokovsg.measurement.dto.acceptableResidualThickness.NewAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.ResponseAcceptableResidualThicknessDto;
import ru.nabokovsg.measurement.dto.acceptableResidualThickness.UpdateAcceptableResidualThicknessDto;

import java.util.List;

public interface AcceptableResidualThicknessService {

    ResponseAcceptableResidualThicknessDto save(NewAcceptableResidualThicknessDto thicknessDto);

    ResponseAcceptableResidualThicknessDto update(UpdateAcceptableResidualThicknessDto thicknessDto);

    ResponseAcceptableResidualThicknessDto get(Long id);

    List<ResponseAcceptableResidualThicknessDto> getAll(Long equipmentLibraryId);

    void delete(Long id);
}