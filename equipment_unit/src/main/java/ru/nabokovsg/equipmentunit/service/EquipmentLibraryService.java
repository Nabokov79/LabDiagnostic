package ru.nabokovsg.equipmentunit.service;

import ru.nabokovsg.equipmentunit.dto.client.ElementLibraryDto;
import ru.nabokovsg.equipmentunit.dto.client.EquipmentLibraryDto;
import ru.nabokovsg.equipmentunit.dto.client.PartElementLibraryDto;

public interface EquipmentLibraryService {

    void updateEquipment(EquipmentLibraryDto equipmentDto);

    void updateElement(ElementLibraryDto elementLibraryDto);

    void updatePartElement(PartElementLibraryDto partElementLibraryDto);
}