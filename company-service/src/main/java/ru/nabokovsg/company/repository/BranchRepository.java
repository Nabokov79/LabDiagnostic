package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.company.model.Branch;

import java.util.Set;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Set<Branch> findAllByOrganizationId(Long organizationId);

    boolean existsByFullName(String fullName);
}