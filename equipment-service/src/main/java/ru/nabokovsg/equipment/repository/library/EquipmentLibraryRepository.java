package ru.nabokovsg.equipment.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.model.library.EquipmentLibrary;

public interface EquipmentLibraryRepository extends JpaRepository<EquipmentLibrary, Long> {
}