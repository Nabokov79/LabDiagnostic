package ru.nabokovsg.equipment.mapper.integration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.equipment.dto.integration.EquipmentDto;
import ru.nabokovsg.equipment.model.equipment.Element;
import ru.nabokovsg.equipment.model.equipment.Equipment;
import ru.nabokovsg.equipment.model.equipment.PartElement;

@Mapper(componentModel = "spring")
public interface EquipmentIntegrationMapper {

    @Mapping(source = "element.equipment.equipmentLibraryId", target = "equipmentLibraryId")
    @Mapping(source = "elementLibraryId", target = "elementLibraryId")
    @Mapping(source = "elementName", target = "elementName")
    @Mapping(source = "standardSize", target = "standardSize")
    @Mapping(source = "thickness", target = "thickness")
    @Mapping(source = "minDiameter", target = "minDiameter")
    @Mapping(source = "minThickness", target = "minThickness")
    @Mapping(source = "maxDiameter", target = "maxDiameter")
    @Mapping(source = "maxThickness", target = "maxThickness")
    @Mapping(target = "partElementLibraryId", ignore = true)
    EquipmentDto mapToElement(Element element);

    @Mapping(source = "partElement.element.equipment.equipmentLibraryId", target = "equipmentLibraryId")
    @Mapping(source = "partElement.element.elementLibraryId", target = "elementLibraryId")
    @Mapping(source = "partElement.element.elementName", target = "elementName")
    @Mapping(source = "partElementLibraryId", target = "partElementLibraryId")
    @Mapping(source = "partElementName", target = "partElementName")
    @Mapping(source = "standardSize", target = "standardSize")
    @Mapping(source = "thickness", target = "thickness")
    @Mapping(source = "minDiameter", target = "minDiameter")
    @Mapping(source = "minThickness", target = "minThickness")
    @Mapping(source = "maxDiameter", target = "maxDiameter")
    @Mapping(source = "maxThickness", target = "maxThickness")
    EquipmentDto mapToPartElement(PartElement partElement);

    EquipmentDto mapToEquipment(Equipment equipment);
}