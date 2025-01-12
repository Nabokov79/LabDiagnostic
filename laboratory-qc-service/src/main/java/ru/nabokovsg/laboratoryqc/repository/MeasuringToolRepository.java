package ru.nabokovsg.laboratoryqc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryqc.model.MeasuringTool;

public interface MeasuringToolRepository extends JpaRepository<MeasuringTool, Long> {
}