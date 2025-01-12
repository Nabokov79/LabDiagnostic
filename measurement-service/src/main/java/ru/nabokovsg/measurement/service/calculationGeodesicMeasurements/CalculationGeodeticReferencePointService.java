package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ReferencePoint;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;
import ru.nabokovsg.measurement.model.library.AcceptableDeviationsGeodesy;

import java.util.List;
import java.util.Set;

public interface CalculationGeodeticReferencePointService {

    void save(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
            , List<GeodesicMeasurements> measurements
            , CalculationGeodeticMeasuring geodeticMeasuringPoints);

    void update(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
              , List<GeodesicMeasurements> measurements
              , Set<ReferencePoint> referencePoints);
}