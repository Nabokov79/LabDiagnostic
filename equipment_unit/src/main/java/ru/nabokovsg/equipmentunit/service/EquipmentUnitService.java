package ru.nabokovsg.equipmentunit.service;

import ru.nabokovsg.equipmentunit.dto.equipment.*;
import ru.nabokovsg.equipmentunit.model.EquipmentUnit;

import java.util.List;

public interface EquipmentUnitService {

    ResponseEquipmentUnitDto save(NewEquipmentUnitDto equipmentDto);

    ResponseEquipmentUnitDto update(UpdateEquipmentUnitDto equipmentDto);

    ResponseEquipmentUnitDto get(Long id);

    List<ResponseEquipmentUnitSourceDto> getAllEquipmentUnitSource(String search);

    List<ResponseEquipmentUnitDeviceDto> getAllEquipmentUnitDevice(String search);

    void delete(Long id);

    EquipmentUnit getById(Long id);
}