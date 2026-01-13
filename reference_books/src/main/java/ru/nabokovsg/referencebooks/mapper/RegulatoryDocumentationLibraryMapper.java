package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.NewRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.ResponseRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.UpdateRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;

@Mapper(componentModel = "spring")
public interface RegulatoryDocumentationLibraryMapper {

    @Mapping(target = "id", ignore = true)
    RegulatoryDocumentationLibrary mapToRegulatoryDocumentation(NewRegulatoryDocumentationLibraryDto documentDto);

    RegulatoryDocumentationLibrary mapToUpdateRegulatoryDocumentation(UpdateRegulatoryDocumentationLibraryDto documentDto);

    ResponseRegulatoryDocumentationLibraryDto mapToResponseRegulatoryDocumentationDto(RegulatoryDocumentationLibrary document);
}