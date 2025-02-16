package ru.nabokovsg.equipment.mapper.equipment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.equipment.model.equipment.Element;
import ru.nabokovsg.equipment.model.equipment.PartElement;
import ru.nabokovsg.equipment.model.equipment.StandardSize;
import ru.nabokovsg.equipment.model.library.PartElementLibrary;

@Mapper(componentModel = "spring")
public interface PartElementMapper {

    @Mapping(source = "partElement.id", target = "partElementLibraryId")
    @Mapping(source = "element", target = "element")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "thickness", ignore = true)
    @Mapping(target = "minDiameter", ignore = true)
    @Mapping(target = "minThickness", ignore = true)
    @Mapping(target = "maxDiameter", ignore = true)
    @Mapping(target = "maxThickness", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    PartElement mapToEquipmentPartElement(Element element, PartElementLibrary partElement);

    void mapWithStandardSize(@MappingTarget PartElement partElement
            , String standardSize
            , StandardSize standardSizeValues);
}