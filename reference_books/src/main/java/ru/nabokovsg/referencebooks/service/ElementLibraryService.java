package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.elementLibrary.NewElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.UpdateElementLibraryDto;
import ru.nabokovsg.referencebooks.model.ElementLibrary;

import java.util.List;

public interface ElementLibraryService {

    ResponseElementLibraryDto save(NewElementLibraryDto elementDto);

    ResponseElementLibraryDto update(UpdateElementLibraryDto elementDto);

    ResponseElementLibraryDto get(Long id);

    List<ResponseElementLibraryDto> getAll(Long equipmentLibraryId);

    List<ResponseElementLibraryDto> copy(Long equipmentLibraryId);

    void delete(Long id);

    ElementLibrary getById(long id);
}