package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.NewDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.ResponseShortDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.dto.diagnosisLibrary.UpdateDiagnosisLibraryDto;
import ru.nabokovsg.referencebooks.model.DiagnosisLibrary;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiagnosisLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "measurements", ignore = true)
    @Mapping(target = "measurementsType", ignore = true)
    DiagnosisLibrary mapToDiagnosisLibrary(NewDiagnosisLibraryDto documentLibraryDto);

    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "measurements", ignore = true)
    @Mapping(target = "measurementsType", ignore = true)
    DiagnosisLibrary mapToUpdateDiagnosisLibrary(UpdateDiagnosisLibraryDto documentLibraryDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diagnosis", ignore = true)
    void mapFields(@MappingTarget DiagnosisLibrary diagnosisLibrary
                                , EquipmentLibrary equipment
                                , String measurements
                                , String measurementsType);

    @Mapping(source = "diagnosis.equipment.equipmentFullName", target = "equipmentFullName")
    @Mapping(source = "diagnosis.diagnosis", target = "diagnosis")
    @Mapping(source = "diagnosis.measurements", target = "measurements")
    ResponseShortDiagnosisLibraryDto mapToResponseShortDiagnosisLibraryDto(DiagnosisLibrary diagnosis);

    @Mapping(source = "diagnosis.equipment.id", target = "equipmentId")
    @Mapping(source = "diagnosis.diagnosis", target = "diagnosis")
    @Mapping(source = "measurementsType", target = "measurementsType")
    ResponseDiagnosisLibraryDto mapToResponseDiagnosisLibraryDto(DiagnosisLibrary diagnosis
                                                               , List<String> measurementsType);
}