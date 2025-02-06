package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;

@Mapper(componentModel = "spring")
public interface MeasurementDuplicateMapper {

    void mapToUpdateDefectMeasurement(@MappingTarget DefectMeasurement defect, String parametersString);

    void mapToUpdateRepairMeasurement(@MappingTarget RepairMeasurement repair, String parametersString);
}