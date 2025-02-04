package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;

import java.util.Set;

public interface CalculationRepairMeasurementService {

    void saveCalculationMinMax(RepairMeasurement repair, Set<RepairMeasurement> repairs);

    void saveWithoutCalculation(RepairMeasurement repair);

    void delete(RepairMeasurement repair);

    void deleteByDefectId(RepairMeasurement repair);
}