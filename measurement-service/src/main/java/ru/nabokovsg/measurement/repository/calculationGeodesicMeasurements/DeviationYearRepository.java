package ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.DeviationYear;

public interface DeviationYearRepository extends JpaRepository<DeviationYear, Long> {
}