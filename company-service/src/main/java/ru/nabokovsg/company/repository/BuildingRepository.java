package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.company.model.Building;

import java.util.Set;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    Set<Building> findAllByExploitationRegionId(Long exploitationRegionId);

    boolean existsByAddress(String address);
}