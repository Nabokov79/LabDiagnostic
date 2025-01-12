package ru.nabokovsg.equipment.service.library;

import ru.nabokovsg.equipment.model.library.EquipmentLibrary;

public interface EquipmentCopyingService {

    void copy(EquipmentLibrary equipment, Long equipmentLibraryId);
}