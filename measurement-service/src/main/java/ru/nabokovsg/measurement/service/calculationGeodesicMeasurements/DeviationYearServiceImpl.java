package ru.nabokovsg.measurement.service.calculationGeodesicMeasurements;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.measurement.mapper.calculationGeodesicMeasurements.DeviationYearMapper;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.DeviationYear;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.QDeviationYear;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ReferencePoint;
import ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements.DeviationYearRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviationYearServiceImpl implements DeviationYearService {

    private final DeviationYearRepository repository;
    private final DeviationYearMapper mapper;
    private final EntityManager em;

    @Override
    public void save(List<ReferencePoint> points) {
        int year = LocalDate.now().getYear();
        repository.saveAll(points.stream()
                                 .map(point -> mapper.mapToDeviationYear(year, point))
                                 .toList());
    }

    @Override
    public void update(List<ReferencePoint> points) {
        int year = LocalDate.now().getYear();
        Map<Long, DeviationYear> deviations = getDeviations(points, year).stream()
                                           .collect(Collectors.toMap(deviation -> deviation.getReferencePoint().getId()
                                                                   , deviation -> deviation));
        points.forEach(point -> {
            DeviationYear deviation = deviations.get(point.getId());
            if (deviation == null) {
                deviations.put(point.getId(),  mapper.mapToDeviationYear(year, point));
            } else {
                deviations.put(point.getId(), mapper.mapToUpdateDeviationYear(deviation, point.getDeviation()));
            }
        });
        repository.saveAll(deviations.values());
    }

    private List<DeviationYear> getDeviations(List<ReferencePoint> points, int year) {
        List<Long> ids = points.stream().map(ReferencePoint::getId).toList();
        QDeviationYear deviation = QDeviationYear.deviationYear;
        return new JPAQueryFactory(em).select(deviation)
                                      .from(deviation)
                                      .where(deviation.referencePoint.id.in(ids), deviation.year.eq(year))
                                      .fetch();
    }
}