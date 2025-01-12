package ru.nabokovsg.laboratoryqc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryqc.model.RegulatoryTechnicalDocumentation;

public interface RegulatoryTechnicalDocumentationRepository extends JpaRepository<RegulatoryTechnicalDocumentation, Long> {

    boolean existsByTitle(String title);
}