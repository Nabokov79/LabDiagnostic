package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.DefectLibrary;

import java.util.Set;

public interface DefectLibraryRepository extends JpaRepository<DefectLibrary, Long> {

    @Query("select d from DefectLibrary d order by d.name")
    Set<DefectLibrary> findAllOrderByName();
}