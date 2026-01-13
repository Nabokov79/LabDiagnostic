package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.partElementLibrary.NewPartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.ResponsePartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.UpdatePartElementLibraryDto;

import java.util.List;

public interface PartElementLibraryService {

    ResponsePartElementLibraryDto save(NewPartElementLibraryDto partElementDto);

    ResponsePartElementLibraryDto update(UpdatePartElementLibraryDto partElementDto);

    ResponsePartElementLibraryDto get(Long id);

    List<ResponsePartElementLibraryDto> getAll(Long elementLibraryId);

    void delete(Long id);
}