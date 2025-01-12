package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.company.model.Department;

import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Set<Department> findByBranchId(Long branchId);

    boolean existsByFullName(String fullName);
}