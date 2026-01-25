package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.NewRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.ResponseRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.ResponseShortRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.dto.regulatoryDocumentationLibrary.UpdateRegulatoryDocumentationLibraryDto;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;

import java.util.List;

public interface RegulatoryDocumentationLibraryService {

    ResponseRegulatoryDocumentationLibraryDto save(NewRegulatoryDocumentationLibraryDto documentationDto);

    ResponseRegulatoryDocumentationLibraryDto update(UpdateRegulatoryDocumentationLibraryDto documentationDto);

    ResponseRegulatoryDocumentationLibraryDto get(Long id);

    List<ResponseShortRegulatoryDocumentationLibraryDto> getAll(String text);

    void delete(Long id);

    RegulatoryDocumentationLibrary getById(Long id);

    String getDocument(Long id);
}