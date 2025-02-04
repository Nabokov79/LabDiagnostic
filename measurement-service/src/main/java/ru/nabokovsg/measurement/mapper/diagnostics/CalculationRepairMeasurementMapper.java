package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.measurement.model.diagnostics.CalculationRepairMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;

@Mapper(componentModel = "spring")
public interface CalculationRepairMeasurementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "repair.id", target = "repairId")
    @Mapping(source = "parametersString", target = "parametersString")
    CalculationRepairMeasurement mapToCalculationRepairMeasurement(RepairMeasurement repair
                                                                 , String parametersString);

    void mapToUpdateMeasuredParameters(@MappingTarget CalculationRepairMeasurement repair
                                                    , String parametersString);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "repair.id", target = "repairId")
    void mapToUpdateCalculationRepairMeasurement(@MappingTarget CalculationRepairMeasurement repairDb
                                                              , RepairMeasurement repair);
}
