package ru.nabokovsg.equipment.mapper.library;

import org.mapstruct.Mapper;
import ru.nabokovsg.equipment.dto.equipmentLibrary.NewEquipmentLibraryDto;
import ru.nabokovsg.equipment.dto.equipmentLibrary.ResponseEquipmentLibraryDto;
import ru.nabokovsg.equipment.dto.equipmentLibrary.UpdateEquipmentLibraryDto;
import ru.nabokovsg.equipment.model.library.EquipmentLibrary;

@Mapper(componentModel = "spring")
public interface EquipmentLibraryMapper {

    EquipmentLibrary mapToEquipmentLibrary(NewEquipmentLibraryDto equipment);

    EquipmentLibrary mapToUpdateEquipmentLibrary(UpdateEquipmentLibraryDto equipment);

    ResponseEquipmentLibraryDto mapResponseEquipmentLibraryDto(EquipmentLibrary equipment);
}