package ru.nabokovsg.equipment.mapper.equipment;

import org.mapstruct.Mapper;
import ru.nabokovsg.equipment.dto.equipmentInspection.NewEquipmentInspectionDto;
import ru.nabokovsg.equipment.dto.equipmentInspection.ResponseEquipmentInspectionDto;
import ru.nabokovsg.equipment.dto.equipmentInspection.UpdateEquipmentInspectionDto;
import ru.nabokovsg.equipment.model.equipment.EquipmentInspection;

@Mapper(componentModel = "spring")
public interface EquipmentInspectionMapper {

    EquipmentInspection mapToEquipmentInspection(NewEquipmentInspectionDto inspectionDto);

    EquipmentInspection mapToUpdateEquipmentInspection(UpdateEquipmentInspectionDto inspectionDto);

    ResponseEquipmentInspectionDto mapToResponseEquipmentInspectionDto(EquipmentInspection inspection);
}