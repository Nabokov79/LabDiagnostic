package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.referencebooks.model.RepairLibrary;

public interface RepairLibraryRepository extends JpaRepository<RepairLibrary, Long> {

    boolean existsByName(String name);
}