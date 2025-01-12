package ru.nabokovsg.measurement.mapper.integration;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.model.integration.InformationEquipment;

@Mapper(componentModel = "spring")
public interface IntegrationMapper {

    InformationEquipment mapToInformationEquipment(EquipmentDto equipment);

    InformationEquipment mapToUpdateInformationEquipment(@MappingTarget InformationEquipment equipment
                                                                      , EquipmentDto equipmentDto);
}