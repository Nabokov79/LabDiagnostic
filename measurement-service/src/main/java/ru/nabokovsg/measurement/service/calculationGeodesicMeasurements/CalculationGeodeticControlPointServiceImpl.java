package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.calculationGeodesicMeasurements.ControlPointMapper;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ControlPoint;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;
import ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements.ControlPointRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationGeodeticControlPointServiceImpl implements CalculationGeodeticControlPointService {

    private final ControlPointRepository repository;
    private final ControlPointMapper mapper;

    @Override
    public List<ControlPoint> save(List<GeodesicMeasurements> measurements
                                 , CalculationGeodeticMeasuring geodeticMeasuringPoints) {
        int min = getMin(measurements);
        return repository.saveAll(measurements
                .stream()
                .map(point -> mapper.mapToControlPoint(point
                                                     , getDeviation(min, point.getControlPointValue())
                                                     , geodeticMeasuringPoints))
                .toList());
    }

    @Override
    public List<ControlPoint> update(List<GeodesicMeasurements> measurements, Set<ControlPoint> controlPoints) {
        int min = getMin(measurements);
        Map<Integer, ControlPoint> points = controlPoints.stream()
                                                    .collect(Collectors.toMap(ControlPoint::getNumberMeasurementLocation
                                                                            , point -> point));
        measurements.forEach(v -> points.put(v.getNumberMeasurementLocation()
                                        , mapper.mapToUpdateControlPoint(points.get(v.getNumberMeasurementLocation())
                                                                      , v
                                                                      , getDeviation(min, v.getControlPointValue()))));
        return repository.saveAll(points.values());
    }

    public int getMin(List<GeodesicMeasurements> measurements) {
        Optional<GeodesicMeasurements> measurement = measurements.stream()
                .min(Comparator.comparing(GeodesicMeasurements::getControlPointValue));
        if (measurement.isPresent()) {
            return measurement.get().getControlPointValue();
        }
        return 0;
    }

    public Integer getDeviation(Integer min, Integer calculatedHeight) {
        return min - calculatedHeight;
    }
}