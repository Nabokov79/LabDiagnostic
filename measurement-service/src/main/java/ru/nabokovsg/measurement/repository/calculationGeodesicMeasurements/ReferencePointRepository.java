package ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ReferencePoint;

public interface ReferencePointRepository extends JpaRepository<ReferencePoint, Long> {
}