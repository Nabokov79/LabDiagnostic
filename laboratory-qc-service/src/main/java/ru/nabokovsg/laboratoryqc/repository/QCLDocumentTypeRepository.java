package ru.nabokovsg.laboratoryqc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryqc.model.QCLDocumentType;

public interface QCLDocumentTypeRepository extends JpaRepository<QCLDocumentType, Long> {

    boolean existsByDiagnosisTypeAndTypeDocumentAndDocumentTitle(String diagnosisType
                                                               , String typeDocument
                                                               , String documentTitle);
}