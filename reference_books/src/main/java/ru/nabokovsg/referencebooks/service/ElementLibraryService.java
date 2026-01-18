package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.elementLibrary.NewElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseShortElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.UpdateElementLibraryDto;
import ru.nabokovsg.referencebooks.model.ElementLibrary;

import java.util.List;

public interface ElementLibraryService {

    ResponseShortElementLibraryDto save(NewElementLibraryDto elementDto);

    ResponseShortElementLibraryDto update(UpdateElementLibraryDto elementDto);

    ResponseElementLibraryDto get(Long id);

    List<ResponseShortElementLibraryDto> getAll(Long id, String name);

    void delete(Long id);

    ElementLibrary getById(long id);
}