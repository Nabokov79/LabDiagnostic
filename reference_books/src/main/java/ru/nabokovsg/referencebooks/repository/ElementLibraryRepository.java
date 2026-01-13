package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.ElementLibrary;

import java.util.Optional;
import java.util.Set;

public interface ElementLibraryRepository extends JpaRepository<ElementLibrary, Long> {

    boolean existsByEquipmentIdAndName(Long equipmentId, String name);

    Set<ElementLibrary> findAllByEquipmentId(Long id);

    @Query("select e.name from ElementLibrary e where e.id = ?1")
    Optional<String> findNameByElementLibraryId(Long id);
}