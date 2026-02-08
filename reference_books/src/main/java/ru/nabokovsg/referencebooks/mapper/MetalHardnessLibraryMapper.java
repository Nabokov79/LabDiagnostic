package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.NewAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseShortAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.UpdateAcceptableMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.model.MetalHardnessLibrary;

@Mapper(componentModel = "spring")
public interface MetalHardnessLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentFullName", ignore = true)
    @Mapping(target = "elementFullName", ignore = true)
    @Mapping(target = "documentationLibrary", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    MetalHardnessLibrary mapToAcceptableHardness(NewAcceptableMetalHardnessLibraryDto hardnessDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentFullName", ignore = true)
    @Mapping(target = "elementFullName", ignore = true)
    @Mapping(target = "documentationLibrary", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    void mapToUpdateAcceptableHardness(@MappingTarget MetalHardnessLibrary hardness
                                                    , UpdateAcceptableMetalHardnessLibraryDto hardnessDto);

    ResponseAcceptableMetalHardnessLibraryDto mapToResponseAcceptableMetalHardnessDto(MetalHardnessLibrary hardness);

    ResponseShortAcceptableMetalHardnessLibraryDto mapToResponseShortAcceptableMetalHardnessLibraryDto(MetalHardnessLibrary hardness);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentationLibraryId", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "elementLibraryId", ignore = true)
    @Mapping(target = "partElementLibraryId", ignore = true)
    @Mapping(target = "diameter", ignore = true)
    @Mapping(target = "thickness", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "minAcceptableHardness", ignore = true)
    @Mapping(target = "maxAcceptableHardness", ignore = true)
    @Mapping(target = "measurementError", ignore = true)
    void mapToMetalHardnessLibrary(@MappingTarget MetalHardnessLibrary metalHardness
                                                , String equipmentFullName
                                                , String elementFullName
                                                , String documentationLibrary
                                                , String standardSize);
}