package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.ResidualThicknessLibrary;

import java.util.Set;

public interface ResidualThicknessLibraryRepository
        extends JpaRepository<ResidualThicknessLibrary, Long> {

    @Query("select t from ResidualThicknessLibrary t order by t.equipmentFullName")
    Set<ResidualThicknessLibrary> findAllOrderByElementNameDesc();
}