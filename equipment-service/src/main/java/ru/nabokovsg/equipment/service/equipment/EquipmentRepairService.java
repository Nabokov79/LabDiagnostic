package ru.nabokovsg.equipment.service.equipment;

import ru.nabokovsg.equipment.dto.equipmentRepair.NewEquipmentRepairDto;
import ru.nabokovsg.equipment.dto.equipmentRepair.ResponseEquipmentRepairDto;
import ru.nabokovsg.equipment.dto.equipmentRepair.UpdateEquipmentRepairDto;

import java.util.List;

public interface EquipmentRepairService {

    ResponseEquipmentRepairDto save(NewEquipmentRepairDto repairDto);

    ResponseEquipmentRepairDto update(UpdateEquipmentRepairDto repairDto);

    List<ResponseEquipmentRepairDto> getAll(Long equipmentId);

    void delete(Long id);
}