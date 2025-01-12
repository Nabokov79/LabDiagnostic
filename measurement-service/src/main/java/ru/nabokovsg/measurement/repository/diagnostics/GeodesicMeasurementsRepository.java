package ru.nabokovsg.measurement.repository.diagnostics;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.diagnostics.GeodesicMeasurements;

import java.util.Set;

public interface GeodesicMeasurementsRepository extends JpaRepository<GeodesicMeasurements, Long> {

    Set<GeodesicMeasurements> findAllByEquipmentId(Long equipmentId);

    GeodesicMeasurements findByEquipmentIdAndNumberMeasurementLocation(Long equipmentId, Integer numberMeasurementLocation);
}