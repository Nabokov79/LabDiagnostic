package ru.nabokovsg.equipment.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.model.equipment.EquipmentPassport;

import java.util.Set;

public interface EquipmentPassportRepository extends JpaRepository<EquipmentPassport, Long> {

    Set<EquipmentPassport> findAllByEquipmentId(Long equipmentId);

    boolean existsByEquipmentIdAndHeader(Long equipmentId, String header);
}