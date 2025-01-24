package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.diagnostics.CalculationRepairMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;

@Mapper(componentModel = "spring")
public interface CalculationRepairMeasurementMapper {

    @Mapping(target = "id", ignore = true)
    CalculationRepairMeasurement mapToCalculationRepairMeasurement(RepairMeasurement repair, String parametersString);

    void mapToUpdateMeasuredParameters(@MappingTarget CalculationRepairMeasurement repair
            , String parametersString);

    void mapToUpdateCalculationRepairMeasurement(@MappingTarget CalculationRepairMeasurement repairDb
                                                              , CalculationRepairMeasurement repair);
}
