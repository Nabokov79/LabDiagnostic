package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecalculateGeodesicMeasurementsServiceImpl implements RecalculateGeodesicMeasurementsService {

    @Override
    public List<GeodesicMeasurements> recalculate(Set<GeodesicMeasurements> measurements) {
        Map<Integer, GeodesicMeasurements> recalculateMeasurements = measurements.stream()
                .collect(Collectors.toMap(GeodesicMeasurements::getSequentialNumber, g -> g));
        int delta = 0;
        for (int i = 1; i < measurements.size() + 1; i++) {
            GeodesicMeasurements measurement = recalculateMeasurements.get(i);
            recalculateMeasurements.put(measurement.getSequentialNumber(), getRecalculateMeasurement(measurement, delta));
            if (measurement.getTransitionValue() != null) {
                delta = getDelta(measurement.getControlPointValue(), measurement.getTransitionValue());
            }
        }
        return new ArrayList<>(recalculateMeasurements.values());
    }

    private GeodesicMeasurements getRecalculateMeasurement(GeodesicMeasurements measurement, int delta) {
        if (measurement.getReferencePointValue() != null) {
            measurement.setReferencePointValue(
                    getSumMeasurementAndDelta(measurement.getReferencePointValue(), delta)
            );
        }
        measurement.setControlPointValue(getSumMeasurementAndDelta(measurement.getControlPointValue(), delta));
        return measurement;
    }

    private Integer getDelta(int measurementValue, int transitionValue) {
        return measurementValue - transitionValue;
    }

    private Integer getSumMeasurementAndDelta(int measurementValue, int delta) {
        return measurementValue + delta;
    }
}