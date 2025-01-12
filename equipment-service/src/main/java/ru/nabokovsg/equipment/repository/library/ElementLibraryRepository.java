package ru.nabokovsg.equipment.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.model.library.ElementLibrary;

import java.util.Set;

public interface ElementLibraryRepository extends JpaRepository<ElementLibrary, Long> {

    boolean existsByEquipmentLibraryIdAndElementName(Long equipmentLibraryId, String elementName);

    Set<ElementLibrary> findAllByEquipmentLibraryId(Long equipmentLibraryId);
}