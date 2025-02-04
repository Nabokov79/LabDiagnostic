package ru.nabokovsg.measurement.service.diagnostics;

import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;

import java.util.Map;
import java.util.Set;

public interface MeasurementDuplicateService {

    DefectMeasurement get(DefectMeasurement defect, Set<DefectMeasurement> defects, Map<Long, Double> parameters);
}