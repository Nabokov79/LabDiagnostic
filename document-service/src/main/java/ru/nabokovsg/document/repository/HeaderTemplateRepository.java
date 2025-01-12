package ru.nabokovsg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.document.model.HeaderTemplate;

public interface HeaderTemplateRepository extends JpaRepository<HeaderTemplate, Long> {
}