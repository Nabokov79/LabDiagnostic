package ru.nabokovsg.measurement.repository.diagnostics;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.diagnostics.RepairMeasurement;

import java.util.Set;

public interface RepairMeasurementRepository extends JpaRepository<RepairMeasurement, Long> {

    Set<RepairMeasurement> findAllByEquipmentId(Long equipmentId);

    Set<RepairMeasurement> findAllByEquipmentIdAndElementId(Long equipmentId, Long elementId);

    Set<RepairMeasurement> findAllByEquipmentIdAndElementIdAndPartElementId(Long equipmentId
                                                                          , Long elementId
                                                                          , Long partElementId);

    Set<RepairMeasurement> findAllByEquipmentIdAndElementIdAndRepairLibraryId(Long equipmentId
                                                                            , Long elementId
                                                                            , Long repairLibraryId);

    Set<RepairMeasurement> findAllByEquipmentIdAndElementIdAndPartElementIdAndRepairLibraryId(Long equipmentId
                                                                                            , Long elementId
                                                                                            , Long partElementId
                                                                                            , Long repairLibraryId);

    Set<RepairMeasurement> findAllByRepairLibraryId(Long repairLibraryId);
}