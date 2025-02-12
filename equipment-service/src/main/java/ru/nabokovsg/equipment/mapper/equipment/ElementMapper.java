package ru.nabokovsg.equipment.mapper.equipment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.equipment.dto.element.NewElementDto;
import ru.nabokovsg.equipment.dto.element.ResponseElementDto;
import ru.nabokovsg.equipment.dto.element.UpdateElementDto;
import ru.nabokovsg.equipment.model.equipment.Equipment;
import ru.nabokovsg.equipment.model.equipment.Element;
import ru.nabokovsg.equipment.model.equipment.StandardSize;
import ru.nabokovsg.equipment.model.library.ElementLibrary;

@Mapper(componentModel = "spring")
public interface ElementMapper {

    @Mapping(source = "elementLibrary.id", target = "elementLibraryId")
    @Mapping(source = "elementLibrary.elementName", target = "elementName")
    @Mapping(source = "equipment", target = "equipment")
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "partsElement", ignore = true)
    @Mapping(target = "id", ignore = true)
    Element mapToElement(ElementLibrary elementLibrary, Equipment equipment);

    void mapWithStandardSize(@MappingTarget Element element, NewElementDto elementDto, String standardSize);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "partsElement", ignore = true)
    void mapToUpdateElement(@MappingTarget Element element, UpdateElementDto elementDto, String standardSize);

    ResponseElementDto mapToResponseEquipmentElementDto(Element element);

    StandardSize mapToStandardSize(NewElementDto elementDto);

    StandardSize mapToUpdateStandardSize(UpdateElementDto elementDto);
}