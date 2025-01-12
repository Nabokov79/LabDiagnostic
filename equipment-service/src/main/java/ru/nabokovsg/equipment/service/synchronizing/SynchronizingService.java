package ru.nabokovsg.equipment.service.synchronizing;

import ru.nabokovsg.equipment.model.library.ElementLibrary;
import ru.nabokovsg.equipment.model.library.EquipmentLibrary;
import ru.nabokovsg.equipment.model.library.PartElementLibrary;

public interface SynchronizingService {

    void updateEquipmentName(EquipmentLibrary equipmentLibrary);

    void updateElementName(ElementLibrary elementLibrary);

    void updatePartElementName(PartElementLibrary partElementLibrary);
}
