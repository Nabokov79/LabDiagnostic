package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;

public interface EquipmentLibraryRepository extends JpaRepository<EquipmentLibrary, Long> {
}