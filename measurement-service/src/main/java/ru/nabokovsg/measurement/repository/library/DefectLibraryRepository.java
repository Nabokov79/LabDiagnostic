package ru.nabokovsg.measurement.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.library.DefectLibrary;

public interface DefectLibraryRepository extends JpaRepository<DefectLibrary, Long> {

    boolean existsByDefectName(String defectName);
}