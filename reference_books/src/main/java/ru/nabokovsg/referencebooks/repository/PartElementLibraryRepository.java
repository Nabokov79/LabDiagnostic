package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;

import java.util.Set;

public interface PartElementLibraryRepository extends JpaRepository<PartElementLibrary, Long> {

    boolean existsByElementIdAndName(Long elementId, String name);

    boolean existsByElementIdAndNameAndPlace(Long elementId, String name, String place);

    Set<PartElementLibrary> findAllByElementId(Long elementId);
}