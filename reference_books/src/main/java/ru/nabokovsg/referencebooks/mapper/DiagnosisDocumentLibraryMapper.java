package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.NewDiagnosisDocumentLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.ResponseDiagnosisDocumentLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisDocumentLibrary.UpdateDiagnosisDocumentLibraryDto;
import ru.nabokovsg.referencebooks.model.DiagnosisDocumentLibrary;

@Mapper(componentModel = "spring")
public interface DiagnosisDocumentLibraryMapper {

    @Mapping(target = "id", ignore = true)
    DiagnosisDocumentLibrary mapToDiagnosisDocumentLibrary(NewDiagnosisDocumentLibraryDto documentDto);

    void mapTuUpdateDiagnosisDocumentLibrary(@MappingTarget DiagnosisDocumentLibrary document
                                                          , UpdateDiagnosisDocumentLibraryDto documentDto);

    ResponseDiagnosisDocumentLibraryDto mapToResponseDiagnosisDocumentLibraryDto(DiagnosisDocumentLibrary document);
}