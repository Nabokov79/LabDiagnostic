package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.referencebooks.dto.repairLibrary.NewRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.ResponseShortRepairLibraryDto;
import ru.nabokovsg.referencebooks.dto.repairLibrary.UpdateRepairLibraryDto;
import ru.nabokovsg.referencebooks.model.RepairLibrary;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RepairLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "measurementParameters", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    RepairLibrary mapToRepairLibrary(NewRepairLibraryDto repairDto);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "measurementParameters", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    RepairLibrary mapToUpdateRepairLibrary(UpdateRepairLibraryDto repairDto);

    ResponseShortRepairLibraryDto mapToResponseShortRepairLibraryDto(RepairLibrary repair);

    ResponseRepairLibraryDto mapToResponseRepairLibraryDto(RepairLibrary repair);

    ResponseRepairLibraryDto mapWithMeasurementParameter(RepairLibrary repair
                                                       , List<MeasurementParameterLibrary> measuredParameters);
}