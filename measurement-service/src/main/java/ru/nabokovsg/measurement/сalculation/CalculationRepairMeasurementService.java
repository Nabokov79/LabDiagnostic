package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;

import java.util.Set;

public interface CalculationRepairMeasurementService {

    void factory(RepairMeasurement repair
               , Set<RepairMeasurement> repairMeasurements
               , ParameterCalculationType calculation);
}
