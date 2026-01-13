package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.elementLibrary.NewElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.UpdateElementLibraryDto;
import ru.nabokovsg.referencebooks.model.ElementLibrary;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;

@Mapper(componentModel = "spring")
public interface ElementLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "partsElement", ignore = true)
    @Mapping(source = "elementDto.diameter", target = "diameter")
    @Mapping(source = "elementDto.length", target = "length")
    @Mapping(source = "elementDto.height", target = "height")
    @Mapping(source = "elementDto.width", target = "width")
    @Mapping(source = "elementDto.thickness", target = "thickness")
    ElementLibrary mapToElementLibrary(NewElementLibraryDto elementDto, EquipmentLibrary equipment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "partsElement", ignore = true)
    void mapToUpdateElementLibrary(@MappingTarget ElementLibrary element, UpdateElementLibraryDto elementDto);

    ResponseElementLibraryDto mapToResponseElementLibraryDto(ElementLibrary element);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "element.diameter", target = "diameter")
    @Mapping(source = "element.length", target = "length")
    @Mapping(source = "element.height", target = "height")
    @Mapping(source = "element.width", target = "width")
    @Mapping(source = "element.thickness", target = "thickness")
    ElementLibrary mapToCopyElementLibrary(ElementLibrary element, EquipmentLibrary equipment);
}