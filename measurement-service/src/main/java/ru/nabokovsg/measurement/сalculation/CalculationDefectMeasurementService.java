package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;

import java.util.Set;

public interface CalculationDefectMeasurementService {

    void save(DefectMeasurement defect, Set<DefectMeasurement> defects, ParameterCalculationType type);
    void delete(DefectMeasurement defect, Set<DefectMeasurement> defects, ParameterCalculationType type);
}