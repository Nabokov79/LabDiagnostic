package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.referencebooks.model.MeasurementParameterLibrary;

public interface MeasurementParameterLibraryRepository extends JpaRepository<MeasurementParameterLibrary, Long> {
}