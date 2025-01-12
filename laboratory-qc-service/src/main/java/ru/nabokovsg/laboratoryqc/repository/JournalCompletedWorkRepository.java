package ru.nabokovsg.laboratoryqc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;

public interface JournalCompletedWorkRepository extends JpaRepository<JournalCompletedWork, Long> {
}