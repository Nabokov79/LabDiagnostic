package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.NewDiagnosisDocumentLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.ResponseDiagnosisDocumentLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.UpdateDiagnosisDocumentLibraryDto;

import java.util.List;

public interface DiagnosisDocumentLibraryService {

    ResponseDiagnosisDocumentLibraryDto save(NewDiagnosisDocumentLibraryDto documentDto);

   ResponseDiagnosisDocumentLibraryDto update(UpdateDiagnosisDocumentLibraryDto documentDto);

    ResponseDiagnosisDocumentLibraryDto get(Long id);

    List<ResponseDiagnosisDocumentLibraryDto> getAll(String name);

    void delete(Long id);
}