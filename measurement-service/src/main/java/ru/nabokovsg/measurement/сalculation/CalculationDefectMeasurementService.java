package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;

import java.util.Set;

public interface CalculationDefectMeasurementService {

    void save(DefectMeasurement defect, Set<DefectMeasurement> defects);
    void delete(DefectMeasurement defect, Set<DefectMeasurement> defects);
}