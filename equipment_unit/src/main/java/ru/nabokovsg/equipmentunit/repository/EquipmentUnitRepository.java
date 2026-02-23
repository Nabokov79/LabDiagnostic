package ru.nabokovsg.equipmentunit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.equipmentunit.model.EquipmentUnit;

import java.util.Set;

public interface EquipmentUnitRepository extends JpaRepository<EquipmentUnit, Long> {

    Set<EquipmentUnit> findAllByEquipmentLibraryId(Long id);

    @Query("select e from EquipmentUnit e where e.structure.heatSupplySourceId is not null")
    Set<EquipmentUnit> findAllEquipmentUnitSource();

    @Query("select e from EquipmentUnit e where e.structure.technicalDeviceId is not null")
    Set<EquipmentUnit> findAllEquipmentUnitDevice();
}