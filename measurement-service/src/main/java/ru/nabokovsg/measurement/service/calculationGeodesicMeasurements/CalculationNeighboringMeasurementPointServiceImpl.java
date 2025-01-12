package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.calculationGeodesicMeasurements.CalculationNeighboringPointMapper;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationNeighboringPoint;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ControlPoint;
import ru.nabokovsg.measurement.model.library.AcceptableDeviationsGeodesy;
import ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements.CalculationNeighboringPointRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationNeighboringMeasurementPointServiceImpl implements CalculationNeighboringMeasurementPointService {

    private final CalculationNeighboringPointRepository repository;
    private final CalculationNeighboringPointMapper mapper;

    @Override
    public void save(List<ControlPoint> controlPoints
                   , AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                   , CalculationGeodeticMeasuring calculationGeodeticPoints) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                .collect(Collectors.toMap(ControlPoint::getNumberMeasurementLocation, c -> c));
        Map<Integer, Integer> neighboringPoints = points.keySet()
                .stream()
                .collect(Collectors.toMap(p -> p
                        , p -> getNeighboringPoints(p, points.size())));
        List<CalculationNeighboringPoint> calculationNeighboringPoints = new ArrayList<>();
        neighboringPoints.forEach((k, v) -> {
            int deviation = Math.abs(points.get(k).getDeviation() - points.get(v).getDeviation());
            calculationNeighboringPoints.add(mapper.mapToCalculationNeighboringPoint(
                                                            k
                                                          , v
                                                          , deviation
                                                          , acceptableDeviation(deviation, acceptableDeviationsGeodesy)
                                                          , calculationGeodeticPoints));

        });
        repository.saveAll(calculationNeighboringPoints);
    }

    @Override
    public void update(List<ControlPoint> controlPoints
                    , AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                    , Set<CalculationNeighboringPoint> calculationNeighboringPoint) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                .collect(Collectors.toMap(ControlPoint::getNumberMeasurementLocation, c -> c));
        calculationNeighboringPoint.forEach(v -> {
            int deviation = Math.abs(points.get(v.getFirstPlaceNumber()).getDeviation()
                                                      - points.get(v.getSecondPlaceNumber()).getDeviation());
            mapper.mapToUpdateNeighboringPoint(v
                                             , deviation
                                             , acceptableDeviation(deviation, acceptableDeviationsGeodesy));
        });
        repository.saveAll(calculationNeighboringPoint);
    }

    private Integer getNeighboringPoints(Integer firstPlace, int size) {
        if (firstPlace != size) {
            return ++firstPlace;
        }
        return 1;
    }

    private boolean acceptableDeviation(int deviation, AcceptableDeviationsGeodesy acceptableDeviationsGeodesy) {
        return deviation > acceptableDeviationsGeodesy.getMaxDifferenceNeighboringPoints();
    }
}