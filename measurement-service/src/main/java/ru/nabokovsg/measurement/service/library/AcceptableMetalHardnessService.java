package ru.nabokovsg.measurement.service.library;

import ru.nabokovsg.measurement.dto.acceptableMetalHardness.NewAcceptableMetalHardnessDto;
import ru.nabokovsg.measurement.dto.acceptableMetalHardness.ResponseAcceptableMetalHardnessDto;
import ru.nabokovsg.measurement.dto.acceptableMetalHardness.UpdateAcceptableMetalHardnessDto;

import java.util.List;

public interface AcceptableMetalHardnessService {

    ResponseAcceptableMetalHardnessDto save(NewAcceptableMetalHardnessDto hardnessDto);

    ResponseAcceptableMetalHardnessDto update(UpdateAcceptableMetalHardnessDto hardnessDto);

    ResponseAcceptableMetalHardnessDto get(Long id);

    List<ResponseAcceptableMetalHardnessDto> getAll(Long equipmentLibraryId);

    void delete(Long id);
}