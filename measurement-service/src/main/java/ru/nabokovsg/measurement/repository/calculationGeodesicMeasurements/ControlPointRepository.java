package ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.ControlPoint;

public interface ControlPointRepository extends JpaRepository<ControlPoint, Long> {
}