package ru.nabokovsg.measurement.repository.diagnostics;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.diagnostics.MeasuredParameter;

public interface MeasuredParameterRepository extends JpaRepository<MeasuredParameter, Long> {
}