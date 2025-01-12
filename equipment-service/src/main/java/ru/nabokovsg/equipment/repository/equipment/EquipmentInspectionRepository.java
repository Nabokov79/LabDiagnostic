package ru.nabokovsg.equipment.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.model.equipment.EquipmentInspection;

import java.util.Set;

public interface EquipmentInspectionRepository extends JpaRepository<EquipmentInspection, Long> {

    Set<EquipmentInspection> findAllByEquipmentId(Long equipmentId);

    boolean existsByEquipmentIdAndDateAndDocumentNumberAndOrganization(Long equipmentId
                                                                     , String date
                                                                     , String documentNumber
                                                                     , String organization);
}