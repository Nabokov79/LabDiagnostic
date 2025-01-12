package ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculatingOppositePoint;

public interface CalculatingOppositePointRepository extends JpaRepository<CalculatingOppositePoint, Long> {
}