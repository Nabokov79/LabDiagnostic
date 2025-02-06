package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;

import java.util.Set;

public interface CalculationRepairMeasurementService {

    void calculationCalculationRepairManager(RepairMeasurement repair, Set<RepairMeasurement> repairs);

    void deleteCalculationRepairManager(RepairMeasurement repair, Set<RepairMeasurement> repairs);

    void delete(RepairMeasurement repair);

    void deleteByDefectId(RepairMeasurement repair);
}