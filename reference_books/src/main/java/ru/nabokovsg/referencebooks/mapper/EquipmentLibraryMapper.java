package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.NewEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.UpdateEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;

@Mapper(componentModel = "spring")
public interface EquipmentLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "elements", ignore = true)
    EquipmentLibrary mapToEquipmentLibrary(NewEquipmentLibraryDto equipment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "elements", ignore = true)
    void mapToUpdateEquipmentLibrary(@MappingTarget EquipmentLibrary equipment, UpdateEquipmentLibraryDto equipmentDto);

    ResponseEquipmentLibraryDto mapToResponseEquipmentLibraryDto(EquipmentLibrary equipment);
}