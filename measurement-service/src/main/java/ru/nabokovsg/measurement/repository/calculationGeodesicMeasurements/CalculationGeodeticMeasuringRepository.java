package ru.nabokovsg.measurement.repository.calculationGeodesicMeasurements;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.calculationGeodesicMeasurements.CalculationGeodeticMeasuring;
import java.util.Optional;

public interface CalculationGeodeticMeasuringRepository extends JpaRepository<CalculationGeodeticMeasuring, Long> {

    Optional<CalculationGeodeticMeasuring> findByEquipmentId(Long equipmentId);
}