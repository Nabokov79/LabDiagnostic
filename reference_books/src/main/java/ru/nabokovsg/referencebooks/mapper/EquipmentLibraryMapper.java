package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.NewEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.ResponseShortEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.dto.equipmentLibrary.UpdateEquipmentLibraryDto;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;

@Mapper(componentModel = "spring")
public interface EquipmentLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "elements", ignore = true)
    @Mapping(target = "dimensions", ignore = true)
    EquipmentLibrary mapToEquipmentLibrary(NewEquipmentLibraryDto equipment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "elements", ignore = true)
    @Mapping(target = "dimensions", ignore = true)
    void mapToUpdateEquipmentLibrary(@MappingTarget EquipmentLibrary equipment, UpdateEquipmentLibraryDto equipmentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "shortName", ignore = true)
    @Mapping(target = "volume", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "periodStabilization", ignore = true)
    @Mapping(target = "diameter", ignore = true)
    @Mapping(target = "length", ignore = true)
    @Mapping(target = "height", ignore = true)
    @Mapping(target = "width", ignore = true)
    @Mapping(target = "elements", ignore = true)
    void mapToDimensions(@MappingTarget EquipmentLibrary equipment, String dimensions);

    ResponseShortEquipmentLibraryDto mapToResponseShortEquipmentLibraryDto(EquipmentLibrary equipment);

    ResponseEquipmentLibraryDto mapToResponseEquipmentLibraryDto(EquipmentLibrary equipment);
}