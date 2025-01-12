package ru.nabokovsg.equipment.service.equipment;

import ru.nabokovsg.equipment.dto.equipmentInspection.NewEquipmentInspectionDto;
import ru.nabokovsg.equipment.dto.equipmentInspection.ResponseEquipmentInspectionDto;
import ru.nabokovsg.equipment.dto.equipmentInspection.UpdateEquipmentInspectionDto;

import java.util.List;

public interface EquipmentInspectionService {

    ResponseEquipmentInspectionDto save(NewEquipmentInspectionDto inspectionDto);

    ResponseEquipmentInspectionDto update(UpdateEquipmentInspectionDto inspectionDto);

    List<ResponseEquipmentInspectionDto> getAll(Long equipmentId);

    void delete(Long id);
}