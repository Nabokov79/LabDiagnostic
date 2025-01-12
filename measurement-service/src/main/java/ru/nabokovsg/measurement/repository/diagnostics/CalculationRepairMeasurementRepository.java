package ru.nabokovsg.measurement.repository.diagnostics;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.diagnostics.CalculationRepairMeasurement;

import java.util.Set;

public interface CalculationRepairMeasurementRepository extends JpaRepository<CalculationRepairMeasurement, Long> {

    Set<CalculationRepairMeasurement> findAllByEquipmentId(Long equipmentId);

    Set<CalculationRepairMeasurement> findAllByEquipmentIdAndElementIdAndPartElementIdAndRepairName(Long equipmentId
            , Long elementId
            , Long partElementId
            , String repairName);

    Set<CalculationRepairMeasurement> findAllByEquipmentIdAndElementIdAndRepairName(Long equipmentId
            , Long elementId
            , String repairName);

    CalculationRepairMeasurement findByEquipmentIdAndElementIdAndPartElementIdAndRepairName(Long equipmentId
            , Long elementId
            , Long partElementId
            , String repairName);

    CalculationRepairMeasurement findByEquipmentIdAndElementIdAndRepairName(Long equipmentId
            , Long elementId
            , String repairName);
}