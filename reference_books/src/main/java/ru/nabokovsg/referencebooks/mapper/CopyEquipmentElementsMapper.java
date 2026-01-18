package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.referencebooks.model.ElementLibrary;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;

@Mapper(componentModel = "spring")
public interface CopyEquipmentElementsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "element.diameter", target = "diameter")
    @Mapping(source = "element.length", target = "length")
    @Mapping(source = "element.height", target = "height")
    @Mapping(source = "element.width", target = "width")
    @Mapping(source = "element.diameterSize", target = "diameterSize")
    @Mapping(source = "element.thicknessSize", target = "thicknessSize")
    @Mapping(source = "element.standardSize", target = "standardSize")
    @Mapping(source = "element.dimensions", target = "dimensions")
    @Mapping(source = "element.partsElement", target = "partsElement")
    @Mapping(source = "equipment", target = "equipment")
    ElementLibrary mapToCopyElementLibrary(ElementLibrary element, EquipmentLibrary equipment);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "partElement.name", target = "name")
    @Mapping(source = "partElement.diameter", target = "diameter")
    @Mapping(source = "partElement.length", target = "length")
    @Mapping(source = "partElement.height", target = "height")
    @Mapping(source = "partElement.width", target = "width")
    @Mapping(source = "partElement.diameterSize", target = "diameterSize")
    @Mapping(source = "partElement.thicknessSize", target = "thicknessSize")
    @Mapping(source = "partElement.standardSize", target = "standardSize")
    @Mapping(source = "partElement.dimensions", target = "dimensions")
    @Mapping(source = "element", target = "element")
    PartElementLibrary mapToCopyPartElementLibrary(PartElementLibrary partElement, ElementLibrary element);
}