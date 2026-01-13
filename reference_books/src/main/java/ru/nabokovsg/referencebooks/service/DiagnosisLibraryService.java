package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.NewDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.UpdateDiagnosisLibraryDto;

import java.util.List;

public interface DiagnosisLibraryService {

    ResponseDiagnosisLibraryDto save(NewDiagnosisLibraryDto diagnosisDto);

    ResponseDiagnosisLibraryDto update(UpdateDiagnosisLibraryDto diagnosisDto);

    ResponseDiagnosisLibraryDto get(Long id);

    List<ResponseDiagnosisLibraryDto> getAll(String name);

    void delete(Long id);
}