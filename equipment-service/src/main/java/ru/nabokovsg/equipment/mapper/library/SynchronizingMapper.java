package ru.nabokovsg.equipment.mapper.library;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.equipment.model.equipment.Element;
import ru.nabokovsg.equipment.model.equipment.Equipment;
import ru.nabokovsg.equipment.model.equipment.PartElement;
import ru.nabokovsg.equipment.model.library.EquipmentLibrary;

@Mapper(componentModel = "spring")
public interface SynchronizingMapper {

    void updateEquipmentName(@MappingTarget Equipment equipment, EquipmentLibrary equipmentLibrary);

    void updateElementName(@MappingTarget Element element, String elementName);

    void updatePartElementName(@MappingTarget PartElement partElement, String partElementName);
}