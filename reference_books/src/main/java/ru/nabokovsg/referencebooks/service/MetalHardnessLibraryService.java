package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.NewAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseShortAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.UpdateAcceptableMetalHardnessLibraryDto;

import java.util.List;

public interface MetalHardnessLibraryService {

    ResponseShortAcceptableMetalHardnessLibraryDto save(NewAcceptableMetalHardnessLibraryDto hardnessDto);

    ResponseShortAcceptableMetalHardnessLibraryDto update(UpdateAcceptableMetalHardnessLibraryDto hardnessDto);

    ResponseAcceptableMetalHardnessLibraryDto get(Long id);

    List<ResponseShortAcceptableMetalHardnessLibraryDto> getAll(String name);

    void delete(Long id);
}