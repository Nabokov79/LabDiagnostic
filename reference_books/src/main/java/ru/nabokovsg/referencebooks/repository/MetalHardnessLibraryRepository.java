package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.MetalHardnessLibrary;

import java.util.Set;

public interface MetalHardnessLibraryRepository extends JpaRepository<MetalHardnessLibrary, Long> {

    @Query("select h from MetalHardnessLibrary h order by h.equipmentFullName desc")
    Set<MetalHardnessLibrary> findAllOrderByEquipmentFullName();

    MetalHardnessLibrary findByPartElementLibraryId(Long partElementLibraryId);

    MetalHardnessLibrary findByElementLibraryId(Long elementLibraryId);
}