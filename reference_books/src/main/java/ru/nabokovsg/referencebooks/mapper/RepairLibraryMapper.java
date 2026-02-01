package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.repairLibrary.NewRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseShortRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.UpdateRepairLibraryDto;
import ru.nabokovsg.referencebooks.model.DefectLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;
import ru.nabokovsg.referencebooks.model.QualityAssessment;
import ru.nabokovsg.referencebooks.model.RepairLibrary;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RepairLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    @Mapping(target = "measuredParametersLibrary", ignore = true)
    RepairLibrary mapToRepairLibrary(NewRepairLibraryDto repairDto);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    @Mapping(target = "measuredParametersLibrary", ignore = true)
    void mapToUpdateRepairLibrary(@MappingTarget RepairLibrary repair, UpdateRepairLibraryDto repairDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "withoutNamingParameter", ignore = true)
    @Mapping(target = "measuredParametersLibrary", ignore = true)
    void mapWithMeasuredParameters(@MappingTarget RepairLibrary repair, String measuredParameters);

    ResponseShortRepairLibraryDto mapToResponseShortRepairLibraryDto(RepairLibrary repair);

    ResponseRepairLibraryDto mapToResponseRepairLibraryDto(RepairLibrary repair);
}