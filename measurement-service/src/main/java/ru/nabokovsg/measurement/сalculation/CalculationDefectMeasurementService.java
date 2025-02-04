package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;

import java.util.Set;

public interface CalculationDefectMeasurementService {

    void calculationCalculationDefectManager(DefectMeasurement defect, Set<DefectMeasurement> defects);

     void deleteCalculationDefectManager(DefectMeasurement defect, Set<DefectMeasurement> defects);

    void delete(DefectMeasurement defect);

    void deleteByDefectId(DefectMeasurement defect);
}