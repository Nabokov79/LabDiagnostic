package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.MetalHardnessLibrary;

import java.util.Set;

public interface MetalHardnessLibraryRepository extends JpaRepository<MetalHardnessLibrary, Long> {

    @Query("select h" +
          " from MetalHardnessLibrary h" +
          " order by h.element.equipment.equipmentFullName desc")
    Set<MetalHardnessLibrary> findAllOrderByEquipmentFullName();

    MetalHardnessLibrary findByPartElementIdAndDocumentationId(Long partElementId, Long documentationId);

    MetalHardnessLibrary findByElementIdAndDocumentationId(Long elementId, Long documentationId);
}