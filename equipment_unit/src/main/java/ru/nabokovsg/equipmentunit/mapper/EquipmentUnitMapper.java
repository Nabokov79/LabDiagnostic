package ru.nabokovsg.equipmentunit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.equipmentunit.dto.equipment.*;
import ru.nabokovsg.equipmentunit.model.EquipmentUnit;
import ru.nabokovsg.equipmentunit.model.StructureOrganization;

@Mapper(componentModel = "spring")
public interface EquipmentUnitMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "elements", ignore = true)
    @Mapping(target = "periodStabilization", ignore = true)
    @Mapping(target = "structure", ignore = true)
    EquipmentUnit mapToEquipmentUnit(NewEquipmentUnitDto equipmentUnitDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "elements", ignore = true)
    @Mapping(target = "periodStabilization", ignore = true)
    @Mapping(target = "structure", ignore = true)
    void mapToUpdateEquipmentUnit(@MappingTarget EquipmentUnit equipment, UpdateEquipmentUnitDto equipmentUnitDto);

    @Mapping(source = "equipment.structure.branch", target = "branch")
    @Mapping(source = "equipment.structure.department", target = "department")
    @Mapping(source = "equipment.structure.heatSupplySource", target = "heatSupplySource")
    ResponseEquipmentUnitSourceDto mapToResponseEquipmentUnitSourceDto(EquipmentUnit equipment);

    @Mapping(source = "equipment.structure.branch", target = "branch")
    @Mapping(source = "equipment.structure.department", target = "department")
    @Mapping(source = "equipment.structure.heatSupplySite", target = "heatSupplySite")
    @Mapping(source = "equipment.structure.technicalDevice", target = "technicalDevice")
    @Mapping(source = "equipment.structure.heatSupplySource", target = "heatSupplySource")
    ResponseEquipmentUnitDeviceDto mapToResponseEquipmentUnitDeviceDto(EquipmentUnit equipment);

    ResponseEquipmentUnitDto mapToResponseEquipmentUnitDto(EquipmentUnit equipment);

    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "stationaryNumber", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "geodesyLocations", ignore = true)
    @Mapping(target = "dateCommissioning", ignore = true)
    @Mapping(target = "periodStabilization", ignore = true)
    @Mapping(target = "diameter", ignore = true)
    @Mapping(target = "length", ignore = true)
    @Mapping(target = "height", ignore = true)
    @Mapping(target = "width", ignore = true)
    @Mapping(target = "elements", ignore = true)
    @Mapping(source = "structure", target = "structure")
    void mapWithStructureOrganization(@MappingTarget EquipmentUnit equipment, StructureOrganization structure);
}