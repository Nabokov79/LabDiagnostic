package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.NewDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseShortDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.UpdateDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.model.DiagnosisLibrary;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiagnosisLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibrary", ignore = true)
    @Mapping(target = "measurements", ignore = true)
    @Mapping(target = "measurementsType", ignore = true)
    DiagnosisLibrary mapToDiagnosisLibrary(NewDiagnosisLibraryDto documentLibraryDto);

    @Mapping(target = "equipmentLibrary", ignore = true)
    @Mapping(target = "measurements", ignore = true)
    @Mapping(target = "measurementsType", ignore = true)
    DiagnosisLibrary mapToUpdateDiagnosisLibrary(UpdateDiagnosisLibraryDto documentLibraryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "diagnosis", ignore = true)
    void mapFields(@MappingTarget DiagnosisLibrary diagnosisLibrary
                                , String equipmentLibrary
                                , String measurements
                                , String measurementsType);

    ResponseShortDiagnosisLibraryDto mapToResponseShortDiagnosisLibraryDto(DiagnosisLibrary diagnosisLibrary);

    @Mapping(source = "measurementsType", target = "measurementsType")
    ResponseDiagnosisLibraryDto mapToResponseDiagnosisLibraryDto(DiagnosisLibrary diagnosisLibrary
                                                               , List<String> measurementsType);
}