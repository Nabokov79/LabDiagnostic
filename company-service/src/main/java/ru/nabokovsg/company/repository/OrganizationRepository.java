package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.company.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    boolean existsByFullName(String fullName);
}