package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.repair.NewRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repair.ResponseRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repair.UpdateRepairMeasurementDto;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;
import ru.nabokovsg.measurement.model.library.RepairLibrary;

@Mapper(componentModel = "spring")
public interface RepairMeasurementMapper {

    @Mapping(target = "id", ignore = true)
    RepairMeasurement mapToRepairMeasurement(NewRepairMeasurementDto repair);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    void mapWithRepairLibrary(@MappingTarget RepairMeasurement repair
                                           , RepairLibrary repairLibrary
                                           , String elementName
                                           , String partElementName);

    RepairMeasurement mapToUpdateRepairMeasurement(UpdateRepairMeasurementDto repairDto);

    ResponseRepairMeasurementDto mapToResponseRepairMeasurementDto(RepairMeasurement repair);
}