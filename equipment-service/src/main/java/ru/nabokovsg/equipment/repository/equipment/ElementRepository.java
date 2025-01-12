package ru.nabokovsg.equipment.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.model.equipment.Element;

import java.util.Set;

public interface ElementRepository extends JpaRepository<Element, Long> {

    Set<Element> findByEquipmentId(Long equipmentId);

    Element findByEquipmentIdAndElementLibraryIdAndStandardSize(Long equipmentId
                                                                       , Long elementLibraryId
                                                                       , String standardSize);

    Element findByEquipmentIdAndElementLibraryId(Long equipmentId, Long elementLibraryId);

    Set<Element> findAllByElementLibraryId(Long elementLibraryId);
}