package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.referencebooks.model.RecommendationLibrary;

import java.util.Set;

public interface RecommendationLibraryRepository extends JpaRepository<RecommendationLibrary, Long> {

    boolean existsByEquipmentLibraryIdAndRecommendation(Long equipmentLibraryId, String recommendation);

    Set<RecommendationLibrary> findAllByEquipmentLibraryId(Long equipmentLibraryId);
}