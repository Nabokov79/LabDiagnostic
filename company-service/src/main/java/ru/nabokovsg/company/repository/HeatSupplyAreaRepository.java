package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.company.model.HeatSupplyArea;

import java.util.Set;

public interface HeatSupplyAreaRepository extends JpaRepository<HeatSupplyArea, Long> {

    boolean existsByFullName(String fullName);

    Set<HeatSupplyArea> findAllByBranchId(Long branchId);
}