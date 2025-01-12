package ru.nabokovsg.measurement.mapper.diagnostics;

import org.mapstruct.Mapper;
import ru.nabokovsg.measurement.dto.calculationMeasurement.ResponseCalculationDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.calculationMeasurement.ResponseCalculationRepairMeasurementDto;
import ru.nabokovsg.measurement.model.diagnostics.CalculationDefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.CalculationRepairMeasurement;

@Mapper(componentModel = "spring")
public interface CalculationMeasurementMapper {

    ResponseCalculationDefectMeasurementDto mapToResponseCalculationDefectMeasurement(CalculationDefectMeasurement defect);

    ResponseCalculationRepairMeasurementDto mapToResponseCalculationRepairMeasurement(CalculationRepairMeasurement repair);
}