package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.NewDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.UpdateDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.model.DiagnosisLibrary;

@Mapper(componentModel = "spring")
public interface DiagnosisLibraryMapper {

    @Mapping(target = "id", ignore = true)
    DiagnosisLibrary mapToDiagnosisLibrary(NewDiagnosisLibraryDto documentLibraryDto);

    DiagnosisLibrary mapToUpdateDiagnosisLibrary(UpdateDiagnosisLibraryDto documentLibraryDto);

    ResponseDiagnosisLibraryDto mapToResponseDiagnosisLibraryDto(DiagnosisLibrary documentLibrary);
}