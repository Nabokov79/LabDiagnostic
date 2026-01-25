package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EquipmentLibraryRepository extends JpaRepository<EquipmentLibrary, Long> {

    @Query("select e.fullName from EquipmentLibrary e where e.id =?1")
    Optional<String> findFullNameById(Long id);

    @Query("select e from EquipmentLibrary e where e.id in :ids")
    Set<EquipmentLibrary> findAllById(@Param("ids")List<Long> ids);
}