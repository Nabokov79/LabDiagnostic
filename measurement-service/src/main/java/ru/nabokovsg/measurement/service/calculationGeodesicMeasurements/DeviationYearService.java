package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ReferencePoint;

import java.util.List;

public interface DeviationYearService {

    void save(List<ReferencePoint> points);

    void update(List<ReferencePoint> points);
}