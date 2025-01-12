package ru.nabokovsg.equipment.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.model.equipment.PartElement;

import java.util.Set;

public interface PartElementRepository extends JpaRepository<PartElement, Long> {

    Set<PartElement> findAllByPartElementLibraryId(Long partElementLibraryId);
}