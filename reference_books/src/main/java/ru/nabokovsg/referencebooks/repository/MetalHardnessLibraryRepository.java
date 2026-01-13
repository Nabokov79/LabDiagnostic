package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.referencebooks.model.MetalHardnessLibrary;

import java.util.Set;

public interface MetalHardnessLibraryRepository extends JpaRepository<MetalHardnessLibrary, Long> {

    Set<MetalHardnessLibrary> findAllByEquipmentLibraryIdOrderByElementNameDesc(Long equipmentLibraryId);

    MetalHardnessLibrary findByPartElementLibraryId(Long partElementLibraryId);

    MetalHardnessLibrary findByElementLibraryId(Long elementLibraryId);
}