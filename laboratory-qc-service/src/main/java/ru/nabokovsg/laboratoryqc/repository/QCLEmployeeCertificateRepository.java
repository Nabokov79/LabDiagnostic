package ru.nabokovsg.laboratoryqc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryqc.model.QCLEmployeeCertificate;

import java.util.Set;

public interface QCLEmployeeCertificateRepository extends JpaRepository<QCLEmployeeCertificate, Long> {

    boolean existsByControlNameAndEmployeeId(String controlName, Long employeeId);
    Set<QCLEmployeeCertificate> findAllByEmployeeId(Long employeeId);
}