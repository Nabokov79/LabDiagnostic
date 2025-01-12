package ru.nabokovsg.equipment.mapper.library;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.equipment.dto.partElementLibrary.NewPartElementLibraryDto;
import ru.nabokovsg.equipment.dto.partElementLibrary.ResponsePartElementLibraryDto;
import ru.nabokovsg.equipment.dto.partElementLibrary.UpdatePartElementLibraryDto;
import ru.nabokovsg.equipment.model.library.ElementLibrary;
import ru.nabokovsg.equipment.model.library.PartElementLibrary;

@Mapper(componentModel = "spring")
public interface PartElementLibraryMapper {

    PartElementLibrary mapToPartElementLibrary(NewPartElementLibraryDto partElement);

    @Mapping(target = "id", ignore = true)
    PartElementLibrary mapToUpdatePartElementLibrary(@MappingTarget PartElementLibrary partElement
                                                                  , String partElementName);


    @Mapping(target = "id", ignore = true)
    void mapWithElementLibrary(@MappingTarget PartElementLibrary partElement, ElementLibrary elementLibrary);

    ResponsePartElementLibraryDto mapToResponsePartElementLibraryDto(PartElementLibrary partElement);

}