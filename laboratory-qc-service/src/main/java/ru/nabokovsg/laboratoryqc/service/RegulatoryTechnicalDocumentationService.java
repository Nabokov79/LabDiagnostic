package ru.nabokovsg.laboratoryqc.service;

import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.NewRegulatoryTechnicalDocumentationDto;
import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.ResponseRegulatoryTechnicalDocumentationDto;
import ru.nabokovsg.laboratoryqc.dto.regulatoryTechnicalDocumentation.UpdateRegulatoryTechnicalDocumentationDto;

import java.util.List;

public interface RegulatoryTechnicalDocumentationService {

    ResponseRegulatoryTechnicalDocumentationDto save(NewRegulatoryTechnicalDocumentationDto documentationDto);

    ResponseRegulatoryTechnicalDocumentationDto update(UpdateRegulatoryTechnicalDocumentationDto documentationDto);

    ResponseRegulatoryTechnicalDocumentationDto get(Long id);

    List<ResponseRegulatoryTechnicalDocumentationDto> getAll(String text);

    void delete(Long id);
}