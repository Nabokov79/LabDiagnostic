package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.calculationGeodesicMeasurements.CalculatingOppositePointMapper;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculatingOppositePoint;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ControlPoint;
import ru.nabokovsg.measurement.model.library.AcceptableDeviationsGeodesy;
import ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements.CalculatingOppositePointRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculatingOppositeMeasurementPointServiceImpl implements CalculatingOppositeMeasurementPointService {

    private final CalculatingOppositePointRepository repository;
    private final CalculatingOppositePointMapper mapper;

    @Override
    public void save(List<ControlPoint> controlPoints
            , AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
            , CalculationGeodeticMeasuring calculationGeodeticPoints) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                .collect(Collectors.toMap(ControlPoint::getNumberMeasurementLocation, c -> c));
        Map<Integer, Integer> diametricalPoints = getDiametricalPoints(points);
        List<CalculatingOppositePoint> oppositePoints = new ArrayList<>();
        diametricalPoints.forEach((k, v) -> {
            int deviation = Math.abs(points.get(k).getDeviation() - points.get(v).getDeviation());
            oppositePoints.add(mapper.mapToCalculatingOppositePoint(
                                                           k
                                                         , v
                                                         , deviation
                                                         , acceptableDeviation(deviation, acceptableDeviationsGeodesy)
                                                         , calculationGeodeticPoints));

        });
        repository.saveAll(oppositePoints);
    }

    @Override
    public void update(List<ControlPoint> controlPoints
                     , AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                     , Set<CalculatingOppositePoint> calculatingOppositePoint) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                .collect(Collectors.toMap(ControlPoint::getNumberMeasurementLocation, c -> c));
        calculatingOppositePoint.forEach(v -> {
            int deviation = Math.abs(points.get(v.getFirstPlaceNumber()).getDeviation()
                                                            - points.get(v.getSecondPlaceNumber()).getDeviation());
            mapper.mapToUpdateCalculatingOppositePoint(v
                                                     , deviation
                                                     , acceptableDeviation(deviation, acceptableDeviationsGeodesy));
        });
        repository.saveAll(calculatingOppositePoint);
    }

    private Map<Integer, Integer> getDiametricalPoints(Map<Integer, ControlPoint> points) {
        int difference = (int) Math.floor(points.keySet().stream().max(Integer::compare).orElse(0) / 2.0);
        if (difference == 0) {
            throw new RuntimeException(
                    String.format("Error in calculating the diametric points, difference=%s", difference)
            );
        }
        return points.keySet()
                     .stream()
                     .filter(i -> i <= difference)
                     .collect(Collectors.toMap(i -> i, i -> i + difference));
    }

    private boolean acceptableDeviation(int deviation, AcceptableDeviationsGeodesy acceptableDeviationsGeodesy) {
        return deviation > acceptableDeviationsGeodesy.getMaxDifferenceDiametricPoints();
    }
}