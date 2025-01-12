package ru.nabokovsg.equipment.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.model.equipment.Equipment;

import java.util.Set;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Set<Equipment> findAllByBuildingId(Long buildingId);

    Set<Equipment> findAllByEquipmentLibraryId(Long equipmentLibraryId);

    boolean existsByEquipmentLibraryIdAndAddressId(Long equipmentLibraryId, Long addressId);

    boolean existsByEquipmentLibraryIdAndAddressIdAndRoom(Long equipmentLibraryId, Long addressId, String room);
}