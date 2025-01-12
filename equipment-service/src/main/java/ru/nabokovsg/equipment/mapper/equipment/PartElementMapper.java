package ru.nabokovsg.equipment.mapper.equipment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.equipment.model.equipment.Element;
import ru.nabokovsg.equipment.model.equipment.PartElement;
import ru.nabokovsg.equipment.model.library.PartElementLibrary;

@Mapper(componentModel = "spring")
public interface PartElementMapper {

    @Mapping(source = "partElement.id", target = "partElementLibraryId")
    @Mapping(source = "partElement.partElementName", target = "partElementName")
    @Mapping(source = "standardSize", target = "standardSize")
    @Mapping(source = "element", target = "element")
    @Mapping(target = "id", ignore = true)
    PartElement mapToEquipmentPartElement(Element element
                                                 , PartElementLibrary partElement
                                                 , String standardSize);

    void mapToUpdateEquipmentPartElement(@MappingTarget PartElement partElement, String standardSize);
}