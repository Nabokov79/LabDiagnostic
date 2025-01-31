package ru.nabokovsg.measurement.repository.diagnostics;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.measurement.model.diagnostics.CalculationDefectMeasurement;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Set;

public interface CalculationDefectMeasurementRepository extends JpaRepository<CalculationDefectMeasurement, Long> {

    Set<CalculationDefectMeasurement> findAllByEquipmentId(Long equipmentId);

    CalculationDefectMeasurement findByEquipmentIdAndElementIdAndPartElementIdAndDefectName(Long equipmentId
                                                                                          , Long elementId
                                                                                          , Long partElementId
                                                                                          , String defectName);

    CalculationDefectMeasurement findByEquipmentIdAndElementIdAndDefectName(Long equipmentId
                                                                          , Long elementId
                                                                          , String defectName);

    CalculationDefectMeasurement findByDefectId(Long defectId);

    @Modifying
    @Transactional
    @Query("delete" +
          " from CalculationDefectMeasurement d" +
          " where d.equipmentId = ?1 and d.elementId = ?2 and d.partElementId = ?3 and d.defectName = ?4")
    void deleteByEquipmentIdAndElementIdAndPartElementIdAndDefectName(Long equipmentId
                                                                    , Long elementId
                                                                    , Long partElementId
                                                                    , String defectName);

    @Modifying
    @Transactional
    @Query("delete" +
          " from CalculationDefectMeasurement d" +
          " where d.equipmentId = ?1 and d.elementId = ?2 and d.defectName = ?3")
    void deleteByEquipmentIdAndElementIdAndDefectName(Long equipmentId, Long elementId, String defectName);

    @Modifying
    @Transactional
    @Query("delete " +
           "from CalculationDefectMeasurement d" +
          " where d.defectId = ?1")
    void deleteByDefectId(Long defectId);
}