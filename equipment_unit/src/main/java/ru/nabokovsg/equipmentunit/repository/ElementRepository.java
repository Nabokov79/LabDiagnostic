package ru.nabokovsg.equipmentunit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipmentunit.model.Element;

import java.util.Set;

public interface ElementRepository extends JpaRepository<Element, Long> {

    Set<Element> findAllByElementLibraryId(Long id);
}