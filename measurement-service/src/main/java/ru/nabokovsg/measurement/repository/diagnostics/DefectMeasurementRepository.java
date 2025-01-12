package ru.nabokovsg.measurement.repository.diagnostics;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.diagnostics.DefectMeasurement;

import java.util.Set;

public interface DefectMeasurementRepository extends JpaRepository<DefectMeasurement, Long> {

    Set<DefectMeasurement> findAllByEquipmentId(Long equipmentId);

    Set<DefectMeasurement> findAllByEquipmentIdAndElementId(Long equipmentId, Long elementId);

    Set<DefectMeasurement> findAllByEquipmentIdAndElementIdAndPartElementId(Long equipmentId
                                                                          , Long elementId
                                                                          , Long partElementId);

    Set<DefectMeasurement> findAllByEquipmentIdAndElementIdAndDefectLibraryId(Long equipmentId
                                                                            , Long elementId
                                                                            , Long defectLibraryId);

    Set<DefectMeasurement> findAllByEquipmentIdAndElementIdAndPartElementIdAndDefectLibraryId(Long equipmentId
                                                                                            , Long elementId
                                                                                            , Long partElementId
                                                                                            , Long defectLibraryId);

    Set<DefectMeasurement> findAllByDefectLibraryId(Long defectLibraryId);
}