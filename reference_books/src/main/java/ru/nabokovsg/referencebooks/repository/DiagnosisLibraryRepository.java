package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.referencebooks.model.DiagnosisLibrary;

public interface DiagnosisLibraryRepository extends JpaRepository<DiagnosisLibrary, Long> {

    boolean existsByDiagnosis(String diagnosis);
}