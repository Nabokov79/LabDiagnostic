package ru.nabokovsg.equipment.mapper.equipment;

import org.mapstruct.Mapper;
import ru.nabokovsg.equipment.dto.equipmentRepair.NewEquipmentRepairDto;
import ru.nabokovsg.equipment.dto.equipmentRepair.ResponseEquipmentRepairDto;
import ru.nabokovsg.equipment.dto.equipmentRepair.UpdateEquipmentRepairDto;
import ru.nabokovsg.equipment.model.equipment.EquipmentRepair;

@Mapper(componentModel = "spring")
public interface EquipmentRepairMapper {

    EquipmentRepair mapToEquipmentRepair(NewEquipmentRepairDto repairDto);

    EquipmentRepair mapToUpdateEquipmentRepair(UpdateEquipmentRepairDto repairDto);

    ResponseEquipmentRepairDto mapToResponseEquipmentRepairDto(EquipmentRepair equipmentRepair);
}