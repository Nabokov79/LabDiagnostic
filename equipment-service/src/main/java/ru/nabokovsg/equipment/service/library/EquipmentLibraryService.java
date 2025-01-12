package ru.nabokovsg.equipment.service.library;

import ru.nabokovsg.equipment.dto.equipmentLibrary.NewEquipmentLibraryDto;
import ru.nabokovsg.equipment.dto.equipmentLibrary.ResponseEquipmentLibraryDto;
import ru.nabokovsg.equipment.dto.equipmentLibrary.UpdateEquipmentLibraryDto;
import ru.nabokovsg.equipment.model.library.EquipmentLibrary;

import java.util.List;

public interface EquipmentLibraryService {

    ResponseEquipmentLibraryDto save(NewEquipmentLibraryDto equipmentDto);

    ResponseEquipmentLibraryDto update(UpdateEquipmentLibraryDto equipmentDto);

    ResponseEquipmentLibraryDto copy(NewEquipmentLibraryDto equipmentDto);

    ResponseEquipmentLibraryDto get(Long id);

    List<ResponseEquipmentLibraryDto> getAll();

    void delete(Long id);

    EquipmentLibrary getById(Long id);
}