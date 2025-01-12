package ru.nabokovsg.equipment.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.model.equipment.RemarkSpecialist;

import java.util.Set;

public interface RemarkSpecialistRepository extends JpaRepository<RemarkSpecialist, Long> {

    Set<RemarkSpecialist> findAllByEquipmentId(Long equipmentId);
}