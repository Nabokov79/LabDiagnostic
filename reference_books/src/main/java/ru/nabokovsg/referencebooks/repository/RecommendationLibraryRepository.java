package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.RecommendationLibrary;

import java.util.Set;

public interface RecommendationLibraryRepository extends JpaRepository<RecommendationLibrary, Long> {

    boolean existsByEquipmentLibraryIdAndRecommendation(Long equipmentLibraryId, String recommendation);

    Set<RecommendationLibrary> findAllByEquipmentLibraryId(Long equipmentLibraryId);

    @Query("select r from RecommendationLibrary r order by r.equipmentLibrary desc")
    Set<RecommendationLibrary> findAllByOrderByEquipmentLibrary();
}