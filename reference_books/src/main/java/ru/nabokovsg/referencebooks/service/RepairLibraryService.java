package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.repairLibrary.NewRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseShortRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.UpdateRepairLibraryDto;
import ru.nabokovsg.referencebooks.model.RepairLibrary;

import java.util.List;

public interface RepairLibraryService {

    ResponseShortRepairLibraryDto save(NewRepairLibraryDto repairDto);

    ResponseShortRepairLibraryDto update(UpdateRepairLibraryDto repairDto);

    ResponseRepairLibraryDto get(Long id);

    List<ResponseShortRepairLibraryDto> getAll(String name);

    void delete(Long id);

    RepairLibrary getById(Long id);
}