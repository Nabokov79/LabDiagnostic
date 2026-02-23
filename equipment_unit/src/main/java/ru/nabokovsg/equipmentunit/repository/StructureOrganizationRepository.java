package ru.nabokovsg.equipmentunit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipmentunit.model.StructureOrganization;

import java.util.Optional;
import java.util.Set;

public interface StructureOrganizationRepository extends JpaRepository<StructureOrganization, Long> {

    Set<StructureOrganization> findAllByBranchId(Long Id);

    Set<StructureOrganization> findAllByDepartmentId(Long Id);

    Set<StructureOrganization> findAllByHeatSupplySiteId(Long id);

    Set<StructureOrganization> findAllByTechnicalDeviceId(Long id);

    Set<StructureOrganization> findAllByHeatSupplySourceId(Long id);

    Optional<StructureOrganization> findByTechnicalDeviceId(Long id);

    Optional<StructureOrganization> findByHeatSupplySourceId(Long id);
}