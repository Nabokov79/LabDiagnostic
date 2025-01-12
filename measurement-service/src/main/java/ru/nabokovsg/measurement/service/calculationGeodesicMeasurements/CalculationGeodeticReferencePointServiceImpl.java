package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.calculationGeodesicMeasurements.ReferencePointMapper;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.DeviationYear;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ReferencePoint;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;
import ru.nabokovsg.measurement.model.library.AcceptableDeviationsGeodesy;
import ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements.ReferencePointRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationGeodeticReferencePointServiceImpl implements CalculationGeodeticReferencePointService {

    private final ReferencePointRepository repository;
    private final ReferencePointMapper mapper;
    private final DeviationYearService deviationYearService;

    @Override
    public void save(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                   , List<GeodesicMeasurements> measurements
                   , CalculationGeodeticMeasuring geodeticMeasuringPoints) {
        int min = getMin(measurements);
        List<ReferencePoint> referencePoints = repository.saveAll(
             measurements.stream()
                         .map(point -> createReferencePoint(acceptableDeviationsGeodesy
                                                          , mapper.mapToReferencePoint(point, geodeticMeasuringPoints)
                                                          , min))
                        .toList());
        deviationYearService.save(referencePoints);
    }

    @Override
    public void update(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                     , List<GeodesicMeasurements> measurements
                     , Set<ReferencePoint> referencePoints) {
        if (measurements.isEmpty()) {
            return;
        }
        Map<Integer, ReferencePoint> references = referencePoints.stream()
                                                .collect(Collectors.toMap(ReferencePoint::getNumberMeasurementLocation
                                                                        , point -> point));
        int min = getMin(measurements);
        List<ReferencePoint> referencePointsDb = repository.saveAll(measurements.stream()
                 .map(point -> createReferencePoint(acceptableDeviationsGeodesy
                         , mapper.mapToUpdateReferencePoint(references.get(point.getNumberMeasurementLocation()), point)
                         , min))
                 .toList());
        deviationYearService.update(referencePointsDb);
    }


    private ReferencePoint createReferencePoint(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
            , ReferencePoint referencePoint
            , Integer min) {
        Integer deviation = getDeviation(min, referencePoint.getReferencePointValue());
        Integer precipitation = getPrecipitation(deviation, referencePoint.getDeviationYeas());
        mapper.addToReferencePoint(referencePoint
                , deviation
                , precipitation
                , comparePrecipitation(precipitation, acceptableDeviationsGeodesy.getAcceptablePrecipitation()));
        return referencePoint;
    }

    public Integer getPrecipitation(Integer newDeviation, List<DeviationYear> deviationYears) {
        if (deviationYears == null) {
            return 0;
        }
        Map<Integer, Integer> deviationYeasDb = deviationYears.stream()
                .collect(Collectors.toMap(DeviationYear::getYear
                        , DeviationYear::getDeviation));
        return newDeviation - deviationYeasDb.get(deviationYeasDb.keySet()
                .stream()
                .mapToInt(d -> d)
                .max()
                .orElseThrow(NoSuchElementException::new));
    }

    private boolean comparePrecipitation(Integer precipitation, Integer acceptablePrecipitation) {
        if (acceptablePrecipitation != null) {
            return precipitation > acceptablePrecipitation;
        }
        return false;
    }

    private int getMin(List<GeodesicMeasurements> measurements) {
        Optional<GeodesicMeasurements> measurement = measurements.stream()
                .filter(referencePoint -> referencePoint.getReferencePointValue() != null)
                .min(Comparator.comparing(GeodesicMeasurements::getReferencePointValue));
        if (measurement.isPresent()) {
            return measurement.get().getReferencePointValue();
        }
        return 0;
    }
    public Integer getDeviation(Integer min, Integer calculatedHeight) {
        return min - calculatedHeight;
    }
}