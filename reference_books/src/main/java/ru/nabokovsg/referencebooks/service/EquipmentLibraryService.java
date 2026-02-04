package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseShortElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.NewEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseShortEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.UpdateEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;

import java.util.List;
import java.util.Set;

public interface EquipmentLibraryService {

    ResponseShortEquipmentLibraryDto save(NewEquipmentLibraryDto equipmentDto);

    ResponseShortEquipmentLibraryDto update(UpdateEquipmentLibraryDto equipmentDto);

    List<ResponseShortElementLibraryDto> copyElements(Long id, Long copyId);

    ResponseEquipmentLibraryDto get(Long id);

    List<ResponseShortEquipmentLibraryDto> getAll(String name);

    void delete(Long id);

    EquipmentLibrary getById(Long id);

    Set<EquipmentLibrary> getAllByIds(List<Long> ids);
}