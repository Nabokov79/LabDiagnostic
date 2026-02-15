package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.NewDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseShortDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.UpdateDiagnosisLibraryDto;

import java.util.List;

public interface DiagnosisLibraryService {

    ResponseShortDiagnosisLibraryDto save(NewDiagnosisLibraryDto diagnosisDto);

    ResponseShortDiagnosisLibraryDto update(UpdateDiagnosisLibraryDto diagnosisDto);

    ResponseDiagnosisLibraryDto get(Long id);

    List<ResponseShortDiagnosisLibraryDto> getAll(String search);

    void delete(Long id);
}