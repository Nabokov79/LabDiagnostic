package ru.nabokovsg.equipment.service.library;

import ru.nabokovsg.equipment.dto.partElementLibrary.NewPartElementLibraryDto;
import ru.nabokovsg.equipment.dto.partElementLibrary.ResponsePartElementLibraryDto;
import ru.nabokovsg.equipment.dto.partElementLibrary.UpdatePartElementLibraryDto;
import ru.nabokovsg.equipment.model.library.PartElementLibrary;

import java.util.List;

public interface PartElementLibraryService {

    ResponsePartElementLibraryDto save(NewPartElementLibraryDto partElementDto);

    ResponsePartElementLibraryDto update(UpdatePartElementLibraryDto partElementDto);

    List<ResponsePartElementLibraryDto> getAll(Long elementLibraryId);

    void delete(Long id);

    PartElementLibrary getById(Long id);
}