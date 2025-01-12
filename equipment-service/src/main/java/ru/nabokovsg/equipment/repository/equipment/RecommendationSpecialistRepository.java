package ru.nabokovsg.equipment.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.model.equipment.RecommendationSpecialist;

import java.util.Set;

public interface RecommendationSpecialistRepository extends JpaRepository<RecommendationSpecialist, Long> {

    boolean existsByEquipmentIdAndRecommendation(Long equipmentId, String recommendation);

    Set<RecommendationSpecialist> findAllByEquipmentId(Long equipmentId);
}