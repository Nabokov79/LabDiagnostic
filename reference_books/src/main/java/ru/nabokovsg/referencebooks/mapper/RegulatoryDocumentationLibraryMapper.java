package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.NewRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.ResponseRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.ResponseShortRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.UpdateRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;
import ru.nabokovsg.referencebooks.model_enum.RegulatoryDocumentationLibraryStatus;
import ru.nabokovsg.referencebooks.model_enum.RegulatoryDocumentationLibraryType;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RegulatoryDocumentationLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "equipments", ignore = true)
    RegulatoryDocumentationLibrary mapToRegulatoryDocumentation(NewRegulatoryDocumentationLibraryDto documentDto);

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "equipments", ignore = true)
    void mapToUpdateRegulatoryDocumentation(@MappingTarget RegulatoryDocumentationLibrary document, UpdateRegulatoryDocumentationLibraryDto documentDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "document", ignore = true)
    @Mapping(target = "documentName", ignore = true)
    @Mapping(target = "areaDistribution", ignore = true)
    @Mapping(target = "equipments", ignore = true)
    @Mapping(target = "documentStatus", ignore = true)
    @Mapping(target = "status", ignore = true)
    void mapWithType(@MappingTarget RegulatoryDocumentationLibrary document
                                  , RegulatoryDocumentationLibraryType type
                                  , String documentType);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "document", ignore = true)
    @Mapping(target = "documentName", ignore = true)
    @Mapping(target = "areaDistribution", ignore = true)
    @Mapping(target = "equipments", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    void mapWithStatus(@MappingTarget RegulatoryDocumentationLibrary document
                                    , RegulatoryDocumentationLibraryStatus status
                                    , String documentStatus);

    @Mapping(source = "equipments", target = "equipments")
    RegulatoryDocumentationLibrary mapWithEquipments(RegulatoryDocumentationLibrary document, Set<EquipmentLibrary> equipments);

    ResponseRegulatoryDocumentationLibraryDto mapToResponseRegulatoryDocumentationDto(RegulatoryDocumentationLibrary document);

    ResponseShortRegulatoryDocumentationLibraryDto mapToResponseShortRegulatoryDocumentationLibraryDto(RegulatoryDocumentationLibrary document);
}