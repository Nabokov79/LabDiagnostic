package ru.nabokovsg.equipment.mapper.library;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.equipment.model.library.ElementLibrary;
import ru.nabokovsg.equipment.model.library.EquipmentLibrary;
import ru.nabokovsg.equipment.model.library.PartElementLibrary;

@Mapper(componentModel = "spring")
public interface EquipmentCopyingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "partsElement", ignore = true)
    @Mapping(source = "equipmentLibrary", target = "equipmentLibrary")
    ElementLibrary mapToCopyElementLibrary(ElementLibrary element, EquipmentLibrary equipmentLibrary);


    @Mapping(target = "id", ignore = true)
    @Mapping(source = "elementLibrary", target = "elementLibrary")
    PartElementLibrary mapToCopyPartElementLibrary(PartElementLibrary partElement, ElementLibrary elementLibrary);
}