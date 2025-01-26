package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.dto.calculationMeasurement.ResponseCalculationDefectMeasurementDto;
import ru.nabokovsg.measurement.dto.calculationMeasurement.ResponseCalculationRepairMeasurementDto;

import java.util.List;

public interface CalculationMeasurementService {

    List<ResponseCalculationDefectMeasurementDto> getAllDefect(Long equipmentId);

    List<ResponseCalculationRepairMeasurementDto> getAllRepair(Long equipmentId);
}