package ru.nabokovsg.equipmentunit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipmentunit.model.PartElement;

import java.util.Set;

public interface PartElementRepository extends JpaRepository<PartElement, Long> {

    Set<PartElement> findAllByPartElementLibraryId(Long id);
}