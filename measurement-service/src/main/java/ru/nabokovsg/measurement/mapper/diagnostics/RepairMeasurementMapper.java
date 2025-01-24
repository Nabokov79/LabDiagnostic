package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.measurement.dto.repair.NewRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repair.ResponseRepairMeasurementDto;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;
import ru.nabokovsg.measurement.model.library.RepairLibrary;

@Mapper(componentModel = "spring")
public interface RepairMeasurementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "measuredParameters", ignore = true)
    RepairMeasurement mapToRepairMeasurement(NewRepairMeasurementDto repair
                                           , RepairLibrary repairLibrary
                                           , String elementName
                                           , String partElementName);

    ResponseRepairMeasurementDto mapToResponseRepairMeasurementDto(RepairMeasurement repair);
}