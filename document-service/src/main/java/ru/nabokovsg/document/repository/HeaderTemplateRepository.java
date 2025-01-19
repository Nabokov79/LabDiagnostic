package ru.nabokovsg.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.document.model.PageTitleTemplate;

public interface HeaderTemplateRepository extends JpaRepository<PageTitleTemplate, Long> {
}