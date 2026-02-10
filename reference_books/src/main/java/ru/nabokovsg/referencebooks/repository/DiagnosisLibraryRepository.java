package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.DiagnosisLibrary;

import java.util.Set;

public interface DiagnosisLibraryRepository extends JpaRepository<DiagnosisLibrary, Long> {

    boolean existsByEquipmentLibraryAndDiagnosis(String equipmentLibrary, String diagnosis);

    @Query("select d.id from DiagnosisLibrary d where d.equipmentLibrary=?1 and d.diagnosis=?2")
    Long findIdByEquipmentLibraryAndDiagnosis(String equipmentLibrary, String diagnosis);

    @Query("select d.id from DiagnosisLibrary d order by d.equipmentLibrary")
    Set<DiagnosisLibrary> findAllOrderByEquipmentLibrary();
}