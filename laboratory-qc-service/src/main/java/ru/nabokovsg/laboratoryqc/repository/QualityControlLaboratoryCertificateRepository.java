package ru.nabokovsg.laboratoryqc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryqc.model.QualityControlLaboratoryCertificate;

public interface QualityControlLaboratoryCertificateRepository extends JpaRepository<QualityControlLaboratoryCertificate, Long> {

    boolean existsByDocumentNameAndDocumentNumberAndOrganization(String documentName
                                                               , String documentNumber
                                                               , String organization);
}