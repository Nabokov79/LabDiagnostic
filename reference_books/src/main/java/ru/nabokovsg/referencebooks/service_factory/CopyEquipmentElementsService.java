package ru.nabokovsg.referencebooks.service_factory;

import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseShortElementLibraryDto;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;

import java.util.List;

public interface CopyEquipmentElementsService {

    List<ResponseShortElementLibraryDto> copyElements(EquipmentLibrary equipment, EquipmentLibrary copyEquipment);
}