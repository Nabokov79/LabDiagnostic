package ru.nabokovsg.laboratoryqc.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.NewRegulatoryTechnicalDocumentationDto;
import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.ResponseRegulatoryTechnicalDocumentationDto;
import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.UpdateRegulatoryTechnicalDocumentationDto;
import ru.nabokovsg.laboratoryqc.model.RegulatoryTechnicalDocumentation;

@Mapper(componentModel = "spring")
public interface RegulatoryTechnicalDocumentationMapper {

    RegulatoryTechnicalDocumentation mapToRegulatoryTechnicalDocumentation(
                                                                NewRegulatoryTechnicalDocumentationDto documentDto);

    RegulatoryTechnicalDocumentation mapToUpdateRegulatoryTechnicalDocumentation(
                                                            UpdateRegulatoryTechnicalDocumentationDto documentDto);

    ResponseRegulatoryTechnicalDocumentationDto mapToResponseRegulatoryTechnicalDocumentationDto(
                                                                        RegulatoryTechnicalDocumentation document);
}