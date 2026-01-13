package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.DepartmentLibrary;

import java.util.Optional;
import java.util.Set;

public interface DepartmentLibraryRepository extends JpaRepository<DepartmentLibrary, Long> {

    Set<DepartmentLibrary> findAllByBranchIdOrderByFullName(Long branchId);

    boolean existsByFullName(String fullName);

    @Query("select d.id from DepartmentLibrary d where d.fullName = ?1")
    Optional<Long> findIdByFullName(String fullName);
}