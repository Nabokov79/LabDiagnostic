package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.referencebooks.model.EmployeeLibrary;

import java.util.Set;

public interface EmployeeLibraryRepository extends JpaRepository<EmployeeLibrary, Long> {

    Set<EmployeeLibrary> findAllByBranchId(Long branchId);

    Set<EmployeeLibrary> findAllByDepartmentId(Long departmentId);

    Set<EmployeeLibrary> findAllBySourceId(Long sourceId);

    boolean existsByEmail(String email);
}