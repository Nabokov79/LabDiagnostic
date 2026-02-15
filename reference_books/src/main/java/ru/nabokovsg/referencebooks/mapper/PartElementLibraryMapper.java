package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.NewPartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.ResponsePartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.ResponseShortPartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.UpdatePartElementLibraryDto;
import ru.nabokovsg.referencebooks.model.ElementLibrary;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;

@Mapper(componentModel = "spring")
public interface PartElementLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "element", ignore = true)
    @Mapping(target = "partElementFullName", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "dimensions", ignore = true)
    PartElementLibrary mapToPartElementLibrary(NewPartElementLibraryDto partElement);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "element", ignore = true)
    @Mapping(target = "partElementFullName", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "dimensions", ignore = true)
    void mapToUpdatePartElementLibrary(@MappingTarget PartElementLibrary partElement
                                                    , UpdatePartElementLibraryDto partElementDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "place", ignore = true)
    @Mapping(target = "diameter", ignore = true)
    @Mapping(target = "length", ignore = true)
    @Mapping(target = "height", ignore = true)
    @Mapping(target = "width",ignore = true)
    @Mapping(target = "diameterSize", ignore = true)
    @Mapping(target = "thicknessSize", ignore = true)
    @Mapping(source = "element", target = "element")
    @Mapping(source = "dimensions", target = "dimensions")
    @Mapping(source = "standardSize", target = "standardSize")
    void mapWithFields(@MappingTarget PartElementLibrary partElement
                                    , ElementLibrary element
                                    , String partElementFullName
                                    , String fullName
                                    , String dimensions
                                    , String standardSize);

    ResponseShortPartElementLibraryDto mapToResponseShortPartElementLibraryDto(PartElementLibrary partElement);

    ResponsePartElementLibraryDto mapToResponsePartElementLibraryDto(PartElementLibrary partElement);
}