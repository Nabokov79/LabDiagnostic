package ru.nabokovsg.equipment.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.model.equipment.EquipmentRepair;

import java.util.Set;

public interface EquipmentRepairRepository extends JpaRepository<EquipmentRepair, Long> {

    Set<EquipmentRepair> findAllByEquipmentId(Long equipmentId);

    boolean existsByEquipmentIdAndDateAndDescriptionAndOrganization(Long equipmentId
                                                                  , String date
                                                                  , String description
                                                                  , String organization);
}