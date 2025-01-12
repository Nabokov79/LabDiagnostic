package ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationNeighboringPoint;

public interface CalculationNeighboringPointRepository extends JpaRepository<CalculationNeighboringPoint, Long> {
}