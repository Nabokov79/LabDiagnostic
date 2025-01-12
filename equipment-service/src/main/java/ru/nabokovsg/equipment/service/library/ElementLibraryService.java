package ru.nabokovsg.equipment.service.library;

import ru.nabokovsg.equipment.dto.elementLibrary.NewElementLibraryDto;
import ru.nabokovsg.equipment.dto.elementLibrary.ResponseElementLibraryDto;
import ru.nabokovsg.equipment.dto.elementLibrary.UpdateElementLibraryDto;
import ru.nabokovsg.equipment.model.library.ElementLibrary;

import java.util.List;

public interface ElementLibraryService {

    ResponseElementLibraryDto save(NewElementLibraryDto elementDto);

    ResponseElementLibraryDto update(UpdateElementLibraryDto elementDto);

    List<ResponseElementLibraryDto> getAll(Long equipmentLibraryId);

    void delete(Long id);

    ElementLibrary getById(long id);
}