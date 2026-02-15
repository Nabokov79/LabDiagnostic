package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.NewMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseShortMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.UpdateMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.model.ElementLibrary;
import ru.nabokovsg.referencebooks.model.MetalHardnessLibrary;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;

@Mapper(componentModel = "spring")
public interface MetalHardnessLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "documentation", ignore = true)
    @Mapping(target = "element", ignore = true)
    @Mapping(target = "partElement", ignore = true)
    MetalHardnessLibrary mapToMetalHardnessLibrary(NewMetalHardnessLibraryDto hardnessDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    @Mapping(target = "documentation", ignore = true)
    @Mapping(target = "element", ignore = true)
    @Mapping(target = "partElement", ignore = true)
    void mapToUpdateMetalHardnessLibrary(@MappingTarget MetalHardnessLibrary metalHardness
                                                    , UpdateMetalHardnessLibraryDto hardnessDto);

    @Mapping(source = "metalHardness.documentation.id", target = "documentationId")
    @Mapping(source = "metalHardness.element.equipment.id", target = "equipmentId")
    @Mapping(source = "metalHardness.element.id", target = "elementId")
    @Mapping(target = "partElementId", ignore = true)
    ResponseMetalHardnessLibraryDto mapToResponseMetalHardnessDtoByElement(MetalHardnessLibrary metalHardness);

    @Mapping(source = "metalHardness.documentation.id", target = "documentationId")
    @Mapping(source = "metalHardness.partElement.element.equipment.id", target = "equipmentId")
    @Mapping(source = "metalHardness.partElement.element.id", target = "elementId")
    @Mapping(source = "metalHardness.partElement.id", target = "partElementId")
    ResponseMetalHardnessLibraryDto mapToResponseMetalHardnessDtoByPartElement(MetalHardnessLibrary metalHardness);

    @Mapping(source = "metalHardness.partElement.element.equipment.equipmentFullName", target = "equipmentFullName")
    @Mapping(source = "metalHardness.partElement.partElementFullName", target = "elementFullName")
    @Mapping(source = "metalHardness.documentation.document", target = "documentation")
    ResponseShortMetalHardnessLibraryDto mapToResponseShortMetalHardnessLibraryDtoByPartElement(MetalHardnessLibrary metalHardness);

    @Mapping(source = "metalHardness.element.equipment.equipmentFullName", target = "equipmentFullName")
    @Mapping(source = "metalHardness.element.name", target = "elementFullName")
    @Mapping(source = "metalHardness.documentation.document", target = "documentation")
    ResponseShortMetalHardnessLibraryDto mapToResponseShortMetalHardnessLibraryDtoByElement(MetalHardnessLibrary metalHardness);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diameter", ignore = true)
    @Mapping(target = "thickness", ignore = true)
    @Mapping(target = "minAcceptableHardness", ignore = true)
    @Mapping(target = "maxAcceptableHardness", ignore = true)
    @Mapping(target = "measurementError", ignore = true)
    @Mapping(target = "partElement", ignore = true)
    @Mapping(source = "element", target = "element")
    @Mapping(source = "documentation", target = "documentation")
    @Mapping(source = "standardSize", target = "standardSize")
    void mapWithElement(@MappingTarget MetalHardnessLibrary metalHardness
                                     , ElementLibrary element
                                     , RegulatoryDocumentationLibrary documentation
                                     , String standardSize);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diameter", ignore = true)
    @Mapping(target = "thickness", ignore = true)
    @Mapping(target = "minAcceptableHardness", ignore = true)
    @Mapping(target = "maxAcceptableHardness", ignore = true)
    @Mapping(target = "measurementError", ignore = true)
    @Mapping(source = "element", target = "element")
    @Mapping(source = "partElement", target = "partElement")
    @Mapping(source = "documentation", target = "documentation")
    @Mapping(source = "standardSize", target = "standardSize")
    void mapWithPartElement(@MappingTarget MetalHardnessLibrary metalHardness
                                         , ElementLibrary element
                                         , PartElementLibrary partElement
                                         , RegulatoryDocumentationLibrary documentation
                                         , String standardSize);
}