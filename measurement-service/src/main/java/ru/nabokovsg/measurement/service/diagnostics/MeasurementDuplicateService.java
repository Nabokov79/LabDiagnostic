package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;

import java.util.Map;
import java.util.Set;

public interface MeasurementDuplicateService {

    DefectMeasurement searchDefectMeasurementDuplicate(Set<DefectMeasurement> defects, Map<Long, Double> parameters);

   RepairMeasurement searchRepairMeasurementDuplicate(Set<RepairMeasurement> repairs, Map<Long, Double> parameters);
}