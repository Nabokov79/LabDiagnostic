package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;

import java.util.Set;

public interface CalculationRepairMeasurementService {

    void save(RepairMeasurement repair, Set<RepairMeasurement> repairs, ParameterCalculationType type);

    void delete(RepairMeasurement repair, Set<RepairMeasurement> repairs, ParameterCalculationType type);
}