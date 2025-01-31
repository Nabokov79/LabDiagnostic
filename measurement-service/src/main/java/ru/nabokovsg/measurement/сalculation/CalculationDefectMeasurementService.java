package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;

import java.util.Set;

public interface CalculationDefectMeasurementService {

    void calculationManager(DefectMeasurement defect, Set<DefectMeasurement> defects);
    void deleteManager(DefectMeasurement defect, Set<DefectMeasurement> defects);
}