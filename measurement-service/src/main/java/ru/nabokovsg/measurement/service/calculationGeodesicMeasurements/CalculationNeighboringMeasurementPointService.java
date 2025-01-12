package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationNeighboringPoint;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ControlPoint;
import ru.nabokovsg.measurement.model.library.AcceptableDeviationsGeodesy;

import java.util.List;
import java.util.Set;

public interface CalculationNeighboringMeasurementPointService {

    void save(List<ControlPoint> controlPoints
            , AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
            , CalculationGeodeticMeasuring calculationGeodeticPoints);

    void update(List<ControlPoint> controlPoints
            , AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
            , Set<CalculationNeighboringPoint> calculationNeighboringPoint);
}