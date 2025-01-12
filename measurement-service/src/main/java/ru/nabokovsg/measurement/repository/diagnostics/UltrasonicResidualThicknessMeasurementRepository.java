package ru.nabokovsg.measurement.repository.diagnostics;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.diagnostics.UltrasonicResidualThicknessMeasurement;

import java.util.Set;

public interface UltrasonicResidualThicknessMeasurementRepository
                                                 extends JpaRepository<UltrasonicResidualThicknessMeasurement, Long> {

    UltrasonicResidualThicknessMeasurement findByEquipmentIdAndElementIdAndMeasurementNumber(Long equipmentId
                                                                                           , Long elementId
                                                                                           , Integer measurementNumber);

    UltrasonicResidualThicknessMeasurement findByEquipmentIdAndElementIdAndPartElementIdAndMeasurementNumber(
                                                                                           Long equipmentId
                                                                                         , Long elementId
                                                                                         , Long partElementId
                                                                                         , Integer measurementNumber);

    Set<UltrasonicResidualThicknessMeasurement> findAllByEquipmentIdOrderByMeasurementNumberDesc(Long equipmentId);

    Set<UltrasonicResidualThicknessMeasurement> findAllByEquipmentIdAndElementId(Long equipmentId, Long elementId);

    Set<UltrasonicResidualThicknessMeasurement> findAllByEquipmentIdAndElementIdAndPartElementId(Long equipmentId
                                                                                               , Long elementId
                                                                                               , Long partElementId);
}