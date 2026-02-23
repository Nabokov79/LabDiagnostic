package ru.nabokovsg.equipmentunit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.equipmentunit.model.Element;
import ru.nabokovsg.equipmentunit.model.EquipmentUnit;
import ru.nabokovsg.equipmentunit.model.PartElement;

@Mapper(componentModel = "spring")
public interface EquipmentLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
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
    @Mapping(target = "structure", ignore = true)
    void mapUpdateEquipmentUnitName(@MappingTarget EquipmentUnit equipment, String fullName);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "elementLibraryId", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "thickness", ignore = true)
    @Mapping(target = "minDiameter", ignore = true)
    @Mapping(target = "minThickness", ignore = true)
    @Mapping(target = "maxDiameter", ignore = true)
    @Mapping(target = "maxThickness", ignore = true)
    @Mapping(target = "partsElement", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    void mapUpdateElementName(@MappingTarget Element element, String elementName);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "partElementLibraryId", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "thickness", ignore = true)
    @Mapping(target = "minDiameter", ignore = true)
    @Mapping(target = "minThickness", ignore = true)
    @Mapping(target = "maxDiameter", ignore = true)
    @Mapping(target = "maxThickness", ignore = true)
    @Mapping(target = "element", ignore = true)
    void mapUpdatePartElementName(@MappingTarget PartElement partsElement, String partElementName);
}