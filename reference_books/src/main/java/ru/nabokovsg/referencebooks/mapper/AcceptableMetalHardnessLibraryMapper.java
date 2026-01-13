package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.NewAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.UpdateAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.model.MetalHardnessLibrary;

@Mapper(componentModel = "spring")
public interface AcceptableMetalHardnessLibraryMapper {

    @Mapping(target = "id", ignore = true)
    MetalHardnessLibrary mapToAcceptableHardness(NewAcceptableMetalHardnessLibraryDto hardnessDto
                                                         , String elementName);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "elementName", ignore = true)
    @Mapping(target = "elementLibraryId", ignore = true)
    @Mapping(target = "partElementLibraryId", ignore = true)
    void mapToUpdateAcceptableHardness(@MappingTarget MetalHardnessLibrary hardness
                                                    , UpdateAcceptableMetalHardnessLibraryDto hardnessDto);

    ResponseAcceptableMetalHardnessLibraryDto mapToResponseAcceptableMetalHardnessDto(
                                                                             MetalHardnessLibrary hardness);
}