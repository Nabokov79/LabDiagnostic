package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.DiagnosisDocumentLibrary;

import java.util.Set;

public interface DiagnosisDocumentLibraryRepository extends JpaRepository<DiagnosisDocumentLibrary, Long> {

    @Query("select d from DiagnosisDocumentLibrary d order by d.document")
    Set<DiagnosisDocumentLibrary> findAllOrderByDocument();

    boolean existsByDocument(String document);

    @Query("select d.id from DiagnosisDocumentLibrary d where d.document=?1")
    Long findIdByDocument(String document);
}