package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.RepairLibrary;

public interface RepairLibraryRepository extends JpaRepository<RepairLibrary, Long> {

    boolean existsByName(String name);

    @Query("select r.id from RepairLibrary r where r.name =?1")
    Long findIdByName(String name);
}