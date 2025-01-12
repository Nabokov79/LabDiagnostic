package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ControlPoint;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;

import java.util.List;
import java.util.Set;

public interface CalculationGeodeticControlPointService {

    List<ControlPoint> save(List<GeodesicMeasurements> measurements
                          , CalculationGeodeticMeasuring geodeticMeasuringPoints);

    List<ControlPoint> update(List<GeodesicMeasurements> measurements, Set<ControlPoint> controlPoints);
}