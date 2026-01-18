package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.partElementLibrary.NewPartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.ResponsePartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.ResponseShortPartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.UpdatePartElementLibraryDto;

import java.util.List;

public interface PartElementLibraryService {

    ResponseShortPartElementLibraryDto save(NewPartElementLibraryDto partElementDto);

    ResponseShortPartElementLibraryDto update(UpdatePartElementLibraryDto partElementDto);

    ResponsePartElementLibraryDto get(Long id);

    List<ResponseShortPartElementLibraryDto> getAll(Long id, String name);

    void delete(Long id);
}