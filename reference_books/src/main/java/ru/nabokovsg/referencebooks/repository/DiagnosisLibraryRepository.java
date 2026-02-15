package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.DiagnosisLibrary;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;

import java.util.Set;

public interface DiagnosisLibraryRepository extends JpaRepository<DiagnosisLibrary, Long> {

    boolean existsByEquipmentAndDiagnosis(EquipmentLibrary equipment, String diagnosis);

    @Query("select d.id from DiagnosisLibrary d where d.equipment=?1 and d.diagnosis=?2")
    Long findIdByEquipmentAndDiagnosis(EquipmentLibrary equipment, String diagnosis);

    @Query("select d from DiagnosisLibrary d order by d.equipment.equipmentFullName")
    Set<DiagnosisLibrary> findAllOrderByEquipmentLibrary();
}