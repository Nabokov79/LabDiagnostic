package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.referencebooks.model.DefectLibrary;

public interface DefectLibraryRepository extends JpaRepository<DefectLibrary, Long> {
}