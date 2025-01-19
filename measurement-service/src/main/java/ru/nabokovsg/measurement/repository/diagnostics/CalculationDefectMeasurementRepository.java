package ru.nabokovsg.measurement.repository.diagnostics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import ru.nabokovsg.measurement.model.diagnostics.CalculationDefectMeasurement;

import java.util.Set;

public interface CalculationDefectMeasurementRepository extends JpaRepository<CalculationDefectMeasurement, Long> {

    Set<CalculationDefectMeasurement> findAllByEquipmentId(Long equipmentId);

    Set<CalculationDefectMeasurement> findAllByEquipmentIdAndElementIdAndPartElementIdAndDefectName(Long equipmentId
                                                                                                  , Long elementId
                                                                                                  , Long partElementId
                                                                                                  , String defectName);

    Set<CalculationDefectMeasurement> findAllByEquipmentIdAndElementIdAndDefectName(Long equipmentId
                                                                                  , Long elementId
                                                                                  , String defectName);

    CalculationDefectMeasurement findByEquipmentIdAndElementIdAndPartElementIdAndDefectName(Long equipmentId
                                                                                          , Long elementId
                                                                                          , Long partElementId
                                                                                          , String defectName);

    CalculationDefectMeasurement findByEquipmentIdAndElementIdAndDefectName(Long equipmentId
                                                                          , Long elementId
                                                                          , String defectName);
}