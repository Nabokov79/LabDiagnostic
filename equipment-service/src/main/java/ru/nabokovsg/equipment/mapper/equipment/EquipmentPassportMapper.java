package ru.nabokovsg.equipment.mapper.equipment;

import org.mapstruct.Mapper;
import ru.nabokovsg.equipment.dto.equipmentPassport.NewEquipmentPassportDto;
import ru.nabokovsg.equipment.dto.equipmentPassport.ResponseEquipmentPassportDto;
import ru.nabokovsg.equipment.dto.equipmentPassport.UpdateEquipmentPassportDto;
import ru.nabokovsg.equipment.model.equipment.EquipmentPassport;

@Mapper(componentModel = "spring")
public interface EquipmentPassportMapper {

    EquipmentPassport mapToEquipmentPassport(NewEquipmentPassportDto passportDto);

    EquipmentPassport mapToUpdateEquipmentPassport(UpdateEquipmentPassportDto passportDto);

    ResponseEquipmentPassportDto mapToResponseEquipmentPassportDto(EquipmentPassport passport);
}