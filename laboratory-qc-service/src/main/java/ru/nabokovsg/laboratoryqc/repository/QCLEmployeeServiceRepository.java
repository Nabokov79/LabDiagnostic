package ru.nabokovsg.laboratoryqc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryqc.model.QCLEmployee;

import java.util.Optional;

public interface QCLEmployeeServiceRepository extends JpaRepository<QCLEmployee, Long> {

    boolean existsByNameAndPatronymicAndSurnameAndEmail(String name, String patronymic, String surname, String email);

    boolean existsByChief(boolean chief);

    Optional<QCLEmployee> findByChief(boolean chief);
}