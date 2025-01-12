package ru.nabokovsg.measurement.repository.qualityControl;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.qualityControl.WeldDefectControl;

import java.util.Set;

public interface WeldDefectControlRepository extends JpaRepository<WeldDefectControl, Long> {

    Set<WeldDefectControl> findAllByEquipmentId(Long equipmentId);
}