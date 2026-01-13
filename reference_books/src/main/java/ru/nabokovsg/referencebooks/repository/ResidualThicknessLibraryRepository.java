package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.referencebooks.model.ResidualThicknessLibrary;

import java.util.Set;

public interface ResidualThicknessLibraryRepository
        extends JpaRepository<ResidualThicknessLibrary, Long> {

    Set<ResidualThicknessLibrary> findAllByEquipmentLibraryIdOrderByElementNameDesc(Long equipmentLibraryId);

    ResidualThicknessLibrary findByElementLibraryIdAndStandardSize(Long elementLibraryId, Double standardSize);

    ResidualThicknessLibrary findByPartElementLibraryIdAndStandardSize(Long partElementLibraryId, Double standardSize);
}