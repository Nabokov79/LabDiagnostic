package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;

import java.util.List;
import java.util.Set;

public interface RecalculateGeodesicMeasurementsService {

    List<GeodesicMeasurements> recalculate(Set<GeodesicMeasurements> measurements);
}