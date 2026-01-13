package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.HeatSupplySourceLibrary;

import java.util.Optional;
import java.util.Set;

public interface HeatSupplySourceLibraryRepository extends JpaRepository<HeatSupplySourceLibrary, Long> {

    Set<HeatSupplySourceLibrary> findAllByDepartmentIdOrderBySource(Long departmentId);

    boolean existsByAddress(String address);

    @Query("select s.id from HeatSupplySourceLibrary s where s.address = ?1")
    Optional<Long> findIdByAddress(String address);
}