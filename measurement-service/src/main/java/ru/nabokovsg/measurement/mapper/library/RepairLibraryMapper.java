package ru.nabokovsg.measurement.mapper.library;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.repairLibrary.NewRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.ResponseRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.ResponseShortRepairLibraryDto;
import ru.nabokovsg.measurement.dto.repairLibrary.UpdateRepairLibraryDto;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;
import ru.nabokovsg.measurement.model.library.RepairLibrary;

@Mapper(componentModel = "spring")
public interface RepairLibraryMapper {

    @Mapping(target = "measuredParameters", ignore = true)
    RepairLibrary mapToTypeRepairLibrary(NewRepairLibraryDto repairDto);

    @Mapping(target = "measuredParameters", ignore = true)
    void mapToUpdateTypeRepairLibrary(@MappingTarget RepairLibrary repair, UpdateRepairLibraryDto repairDto);

    void mapWithParameterCalculationType(@MappingTarget RepairLibrary repair, ParameterCalculationType calculation);

    ResponseRepairLibraryDto mapToResponseTypeRepairLibraryDto(RepairLibrary repair);

    ResponseShortRepairLibraryDto mapToResponseShortTypeRepairLibraryDto(RepairLibrary repair);
}