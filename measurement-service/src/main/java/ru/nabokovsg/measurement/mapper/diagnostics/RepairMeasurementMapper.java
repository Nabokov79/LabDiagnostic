package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.NewRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.ResponseRepairMeasurementDto;
import ru.nabokovsg.measurement.dto.repairMeasurement.ResponseShortRepairMeasurementDto;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;
import ru.nabokovsg.measurement.model.library.RepairLibrary;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RepairMeasurementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "measuredParameters", target = "measuredParameters")
    RepairMeasurement mapToRepairMeasurement(NewRepairMeasurementDto repair
                                           , Set<MeasuredParameter> measuredParameters
                                           , RepairLibrary repairLibrary
                                           , EquipmentDto equipment
                                           , String parametersString);

    ResponseRepairMeasurementDto mapToResponseRepairMeasurementDto(RepairMeasurement repair);

    ResponseShortRepairMeasurementDto mapToResponseShortRepairMeasurementDto(RepairMeasurement repair);

    void mapToParametersString(@MappingTarget RepairMeasurement repair, String parametersString);
}