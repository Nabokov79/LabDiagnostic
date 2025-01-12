package ru.nabokovsg.measurement.service.library;

import ru.nabokovsg.measurement.dto.repairLibrary.NewRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.ResponseRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.ResponseShortRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.UpdateRepairLibraryDto;
import ru.nabokovsg.measurement.model.library.DefectLibrary;
import ru.nabokovsg.measurement.model.library.RepairLibrary;

import java.util.List;

public interface RepairLibraryService {

    ResponseRepairLibraryDto save(NewRepairLibraryDto repairDto);

    ResponseRepairLibraryDto update(UpdateRepairLibraryDto repairDto);

    ResponseRepairLibraryDto get(Long id);

    List<ResponseShortRepairLibraryDto> getAll();

    void delete(Long id);

    RepairLibrary getById(Long id);
}