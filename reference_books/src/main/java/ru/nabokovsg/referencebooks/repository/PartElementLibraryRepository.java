package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.ElementLibrary;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;

import java.util.Set;

public interface PartElementLibraryRepository extends JpaRepository<PartElementLibrary, Long> {

    boolean existsByElementAndFullName(ElementLibrary element, String fullName);

    @Query("select p.id from PartElementLibrary p where p.element = ?1 and p.fullName = ?2")
    Long findIdByElementAndFullName(ElementLibrary element, String fullName);

    Set<PartElementLibrary> findAllByElementIdOrderByFullNameDesc(Long elementId);
}