package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;

import java.util.Set;

public interface RegulatoryDocumentationLibraryRepository extends JpaRepository<RegulatoryDocumentationLibrary, Long> {

    boolean existsByViewAndNumber(String view, String number);

    @Query("select d from RegulatoryDocumentationLibrary d order by d.view, d.number")
    Set<RegulatoryDocumentationLibrary> findAllOrderByView();

    @Query("select d from RegulatoryDocumentationLibrary d order by d.number")
    Set<RegulatoryDocumentationLibrary> findAllOrderByNumber();
}