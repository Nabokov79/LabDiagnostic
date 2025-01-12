package ru.nabokovsg.measurement.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.library.MeasurementParameterLibrary;

import java.util.Set;

public interface MeasuredParameterLibraryRepository extends JpaRepository<MeasurementParameterLibrary, Long> {

    Set<MeasurementParameterLibrary> findAllByDefectId(Long defectId);
}