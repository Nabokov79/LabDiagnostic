package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.elementLibrary.NewElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseShortElementLibraryDto;
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
    @Mapping(source = "elementDto.diameterSize", target = "diameterSize")
    @Mapping(source = "elementDto.thicknessSize", target = "thicknessSize")
    @Mapping(source = "equipment", target = "equipment")
    @Mapping(source = "standardSize", target = "standardSize")
    @Mapping(source = "dimensions", target = "dimensions")
    ElementLibrary mapToElementLibrary(NewElementLibraryDto elementDto, EquipmentLibrary equipment
            , String dimensions, String standardSize);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "partsElement", ignore = true)
    @Mapping(source = "standardSize", target = "standardSize")
    @Mapping(source = "dimensions", target = "dimensions")
    void mapToUpdateElementLibrary(@MappingTarget ElementLibrary element, UpdateElementLibraryDto elementDto
            , String dimensions, String standardSize);

    ResponseElementLibraryDto mapToResponseElementLibraryDto(ElementLibrary element);

    ResponseShortElementLibraryDto mapToResponseShortElementLibraryDto(ElementLibrary element);
}