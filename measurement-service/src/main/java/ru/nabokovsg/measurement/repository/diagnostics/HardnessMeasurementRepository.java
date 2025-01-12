package ru.nabokovsg.measurement.repository.diagnostics;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.diagnostics.HardnessMeasurement;

import java.util.Set;

public interface HardnessMeasurementRepository extends JpaRepository<HardnessMeasurement, Long> {

    Set<HardnessMeasurement> findAllByEquipmentId(Long equipmentId);

    Set<HardnessMeasurement> findAllByEquipmentIdAndElementId(Long equipmentId, Long elementId);

    Set<HardnessMeasurement> findAllByEquipmentIdAndElementIdAndPartElementId(Long equipmentId
                                                                            , Long elementId
                                                                            , Long partElementId);

    boolean existsByEquipmentIdAndElementIdAndMeasurementNumber(Long equipmentId
                                                              , Long elementId
                                                              , Integer measurementNumber);

    boolean existsByEquipmentIdAndElementIdAndPartElementIdAndMeasurementNumber(Long equipmentId
                                                                              , Long elementId
                                                                              , Long partElementId
                                                                              , Integer measurementNumber);
}