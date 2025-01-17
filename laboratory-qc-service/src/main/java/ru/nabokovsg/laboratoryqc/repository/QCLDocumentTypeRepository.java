package ru.nabokovsg.laboratoryqc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryqc.model.QCLDocumentType;

public interface QCLDocumentTypeRepository extends JpaRepository<QCLDocumentType, Long> {

    boolean existsByDiagnosisTypeAndTypeAndDocumentTitle(String diagnosisType
                                                               , String type
                                                               , String documentTitle);
}