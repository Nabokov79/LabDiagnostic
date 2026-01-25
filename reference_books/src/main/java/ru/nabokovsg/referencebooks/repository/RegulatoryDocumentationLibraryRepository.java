package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;

import java.util.Optional;
import java.util.Set;

public interface RegulatoryDocumentationLibraryRepository extends JpaRepository<RegulatoryDocumentationLibrary, Long> {

    boolean existsByDocument(String document);

    @Query("select d.id from RegulatoryDocumentationLibrary d where d.document = ?1")
    Long findIdByDocument(String document);

    @Query("select d from RegulatoryDocumentationLibrary d order by d.fullName")
    Set<RegulatoryDocumentationLibrary> findAllOrderByFullName();

    @Query("select d.document from RegulatoryDocumentationLibrary d where d.id =?1")
    Optional<String> findDocumentById(Long id);
}