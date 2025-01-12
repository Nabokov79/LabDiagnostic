package ru.nabokovsg.equipment.mapper.equipment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.equipment.dto.equipment.NewEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.ResponseEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.ResponseShortEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.UpdateEquipmentDto;
import ru.nabokovsg.equipment.model.equipment.Equipment;
import ru.nabokovsg.equipment.model.library.EquipmentLibrary;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "elements", ignore = true)
    Equipment mapToEquipment(NewEquipmentDto equipmentDto, EquipmentLibrary equipmentLibrary);

    @Mapping(target = "elements", ignore = true)
    Equipment mapToUpdateEquipment(UpdateEquipmentDto equipmentDto);

    ResponseShortEquipmentDto mapToResponseShortEquipmentDto(Equipment equipment);

    ResponseEquipmentDto mapToResponseEquipmentDto(Equipment equipment);
}