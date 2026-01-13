package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.referencebooks.model.DeviationsGeodesyLibrary;

import java.util.Set;

public interface DeviationsGeodesyLibraryRepository extends JpaRepository<DeviationsGeodesyLibrary, Long> {

    boolean existsByEquipmentLibraryIdAndHeatCarrierAndEquipmentCondition(Long equipmentLibraryId,
                                                                          String heatCarrier,
                                                                          String condition);

    Set<DeviationsGeodesyLibrary> findAllByEquipmentLibraryIdOrderByHeatCarrier(Long equipmentLibraryId);
}