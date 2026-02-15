package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.NewMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseShortMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.UpdateMetalHardnessLibraryDto;

import java.util.List;

public interface MetalHardnessLibraryService {

    ResponseShortMetalHardnessLibraryDto save(NewMetalHardnessLibraryDto hardnessDto);

    ResponseShortMetalHardnessLibraryDto update(UpdateMetalHardnessLibraryDto hardnessDto);

    ResponseMetalHardnessLibraryDto get(Long id);

    List<ResponseShortMetalHardnessLibraryDto> getAll(String search);

    void delete(Long id);
}