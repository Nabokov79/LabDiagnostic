package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.NewAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.UpdateAcceptableMetalHardnessLibraryDto;

import java.util.List;

public interface MetalHardnessLibraryService {

    ResponseAcceptableMetalHardnessLibraryDto save(NewAcceptableMetalHardnessLibraryDto hardnessDto);

    ResponseAcceptableMetalHardnessLibraryDto update(UpdateAcceptableMetalHardnessLibraryDto hardnessDto);

    ResponseAcceptableMetalHardnessLibraryDto get(Long id);

    List<ResponseAcceptableMetalHardnessLibraryDto> getAll(Long equipmentLibraryId);

    void delete(Long id);
}