package ru.nabokovsg.measurement.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.library.AcceptableDeviationsGeodesy;

import java.util.Set;

public interface AcceptableDeviationsGeodesyRepository extends JpaRepository<AcceptableDeviationsGeodesy, Long> {

    boolean existsByEquipmentLibraryIdAndFullAndOld(Long equipmentLibraryId, Boolean full, Boolean old);

    AcceptableDeviationsGeodesy findByEquipmentLibraryIdAndFullAndOld(Long equipmentLibraryId, Boolean full, Boolean old);

    Set<AcceptableDeviationsGeodesy> findAllByEquipmentLibraryId(Long equipmentLibraryId);
}