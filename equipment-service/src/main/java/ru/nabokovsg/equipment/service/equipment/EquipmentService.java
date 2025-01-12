package ru.nabokovsg.equipment.service.equipment;

import ru.nabokovsg.equipment.dto.equipment.NewEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.ResponseEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.ResponseShortEquipmentDto;
import ru.nabokovsg.equipment.dto.equipment.UpdateEquipmentDto;
import ru.nabokovsg.equipment.model.equipment.Equipment;

import java.util.List;

public interface EquipmentService {

    ResponseEquipmentDto save(NewEquipmentDto equipmentDto);

    ResponseEquipmentDto update(UpdateEquipmentDto equipmentDto);

    ResponseEquipmentDto get(Long id);

    List<ResponseShortEquipmentDto> getAll(Long buildingId);

    void delete(Long id);

    Equipment getById(Long id);
}