package ru.nabokovsg.equipment.service.equipment;

import ru.nabokovsg.equipment.dto.equipmentPassport.NewEquipmentPassportDto;
import ru.nabokovsg.equipment.dto.equipmentPassport.ResponseEquipmentPassportDto;
import ru.nabokovsg.equipment.dto.equipmentPassport.UpdateEquipmentPassportDto;

import java.util.List;

public interface EquipmentPassportService {

    ResponseEquipmentPassportDto save(NewEquipmentPassportDto passportDto);

    ResponseEquipmentPassportDto update(UpdateEquipmentPassportDto passportDto);

    List<ResponseEquipmentPassportDto> getAll(Long id);

    void delete(Long id);
}