package ru.nabokovsg.measurement.сalculation;

import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;
import ru.nabokovsg.measurement.model.library.ParameterCalculationType;

import java.util.Set;

public interface CalculationDefectMeasurementService {

    void factory(DefectMeasurement defect
               , Set<DefectMeasurement> defectMeasurements
               , ParameterCalculationType calculation);
}