package ru.nabokovsg.measurement.repository.diagnostics;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.measurement.model.diagnostics.CalculationRepairMeasurement;

import java.util.Set;

public interface CalculationRepairMeasurementRepository extends JpaRepository<CalculationRepairMeasurement, Long> {

    Set<CalculationRepairMeasurement> findAllByEquipmentId(Long equipmentId);
    CalculationRepairMeasurement findByRepairId(Long repairId);

    @Modifying
    @Transactional
    @Query("delete" +
            " from CalculationRepairMeasurement r" +
            " where r.equipmentId = ?1 and r.elementId = ?2 and r.partElementId = ?3 and r.repairName = ?4")
    void deleteByEquipmentIdAndElementIdAndPartElementIdAndRepairName(Long equipmentId
            , Long elementId
            , Long partElementId
            , String repairName);

    @Modifying
    @Transactional
    @Query("delete" +
            " from CalculationRepairMeasurement r" +
            " where r.equipmentId = ?1 and r.elementId = ?2 and r.repairName = ?3")
    void deleteByEquipmentIdAndElementIdAndRepairName(Long equipmentId, Long elementId, String repairName);

    @Modifying
    @Transactional
    @Query("delete " +
            "from CalculationRepairMeasurement r" +
            " where r.repairId = ?1")
    void deleteByRepairId(Long repairId);

    CalculationRepairMeasurement findByEquipmentIdAndElementIdAndPartElementIdAndRepairName(Long equipmentId
            , Long elementId
            , Long partElementId
            , String repairName);

    CalculationRepairMeasurement findByEquipmentIdAndElementIdAndRepairName(Long equipmentId
            , Long elementId
            , String repairName);
}