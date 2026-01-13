package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.equipmentLibrary.NewEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.UpdateEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;

import java.util.List;

public interface EquipmentLibraryService {

    ResponseEquipmentLibraryDto save(NewEquipmentLibraryDto equipmentDto);

    ResponseEquipmentLibraryDto update(UpdateEquipmentLibraryDto equipmentDto);

    ResponseEquipmentLibraryDto get(Long id);

    List<ResponseEquipmentLibraryDto> getAll(String name);

    void delete(Long id);

    EquipmentLibrary getById(Long id);
}