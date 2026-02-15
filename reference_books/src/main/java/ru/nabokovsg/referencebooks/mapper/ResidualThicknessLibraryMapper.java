package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.NewResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.ResponseShortResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.dto.residualThicknessLibrary.UpdateResidualThicknessLibraryDto;
import ru.nabokovsg.referencebooks.model.ElementLibrary;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;
import ru.nabokovsg.referencebooks.model.ResidualThicknessLibrary;

@Mapper(componentModel = "spring")
public interface ResidualThicknessLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentation", ignore = true)
    @Mapping(target = "element", ignore = true)
    @Mapping(target = "partElement", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    ResidualThicknessLibrary mapToResidualThickness(NewResidualThicknessLibraryDto residualThicknessDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentation", ignore = true)
    @Mapping(target = "element", ignore = true)
    @Mapping(target = "partElement", ignore = true)
    @Mapping(target = "standardSize", ignore = true)
    void mapToUpdateResidualThickness(@MappingTarget ResidualThicknessLibrary residualThickness
            , UpdateResidualThicknessLibraryDto residualThicknessDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diameter", ignore = true)
    @Mapping(target = "thickness", ignore = true)
    @Mapping(target = "minAcceptableThicknessMM", ignore = true)
    @Mapping(target = "minAcceptableThicknessPercent", ignore = true)
    @Mapping(target = "maxAcceptableThinningMM", ignore = true)
    @Mapping(target = "maxAcceptableThinningPercent", ignore = true)
    @Mapping(target = "measurementError", ignore = true)
    @Mapping(target = "partElement", ignore = true)
    @Mapping(source = "element", target = "element")
    @Mapping(source = "documentation", target = "documentation")
    @Mapping(source = "standardSize", target = "standardSize")
    void mapWithElement(@MappingTarget ResidualThicknessLibrary residualThickness
                                     , ElementLibrary element
                                     , RegulatoryDocumentationLibrary documentation
                                     , String standardSize);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diameter", ignore = true)
    @Mapping(target = "thickness", ignore = true)
    @Mapping(target = "minAcceptableThicknessMM", ignore = true)
    @Mapping(target = "minAcceptableThicknessPercent", ignore = true)
    @Mapping(target = "maxAcceptableThinningMM", ignore = true)
    @Mapping(target = "maxAcceptableThinningPercent", ignore = true)
    @Mapping(target = "measurementError", ignore = true)
    @Mapping(source = "element", target = "element")
    @Mapping(source = "partElement", target = "partElement")
    @Mapping(source = "documentation", target = "documentation")
    @Mapping(source = "standardSize", target = "standardSize")
    void mapWithPartElement(@MappingTarget ResidualThicknessLibrary residualThickness
                                         , ElementLibrary element
                                         , PartElementLibrary partElement
                                         , RegulatoryDocumentationLibrary documentation
                                         , String standardSize);

    @Mapping(source = "residualThickness.documentation.id", target = "documentationId")
    @Mapping(source = "residualThickness.element.equipment.id", target = "equipmentId")
    @Mapping(source = "residualThickness.element.id", target = "elementId")
    @Mapping(target = "partElementId", ignore = true)
    ResponseResidualThicknessLibraryDto mapToResponseResidualThicknessDtoByElement(ResidualThicknessLibrary residualThickness);

    @Mapping(source = "residualThickness.documentation.id", target = "documentationId")
    @Mapping(source = "residualThickness.partElement.element.equipment.id", target = "equipmentId")
    @Mapping(source = "residualThickness.partElement.element.id", target = "elementId")
    @Mapping(source = "residualThickness.partElement.id", target = "partElementId")
    ResponseResidualThicknessLibraryDto mapToResponseResidualThicknessDtoByPartElement(ResidualThicknessLibrary residualThickness);

    @Mapping(source = "residualThickness.element.equipment.equipmentFullName", target = "equipmentFullName")
    @Mapping(source = "residualThickness.element.name", target = "elementFullName")
    @Mapping(source = "residualThickness.documentation.document", target = "documentation")
    ResponseShortResidualThicknessLibraryDto mapToResponseShortResidualThicknessLibraryDtoByByElement(ResidualThicknessLibrary residualThickness);

    @Mapping(source = "residualThickness.partElement.element.equipment.equipmentFullName", target = "equipmentFullName")
    @Mapping(source = "residualThickness.partElement.partElementFullName", target = "elementFullName")
    @Mapping(source = "residualThickness.documentation.document", target = "documentation")
    ResponseShortResidualThicknessLibraryDto mapToResponseShortResidualThicknessLibraryDtoByByPartElement(ResidualThicknessLibrary residualThickness);
}