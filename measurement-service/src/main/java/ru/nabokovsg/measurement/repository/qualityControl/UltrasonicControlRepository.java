package ru.nabokovsg.measurement.repository.qualityControl;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.qualityControl.UltrasonicControl;

import java.util.Set;

public interface UltrasonicControlRepository extends JpaRepository<UltrasonicControl, Long> {

    Set<UltrasonicControl> findAllByEquipmentId(Long equipmentId);
}