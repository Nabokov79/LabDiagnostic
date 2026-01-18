package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;

import java.util.Set;

public interface PartElementLibraryRepository extends JpaRepository<PartElementLibrary, Long> {

    boolean existsByElementIdAndFullName(Long elementId, String fullName);
    @Query("select p.id from PartElementLibrary p where p.element.id = ?1 and p.fullName = ?2")
    Long findIdByElementIdAndFullName(Long elementId, String fullName);

    Set<PartElementLibrary> findAllByElementIdOrderByFullName(Long elementId);
}