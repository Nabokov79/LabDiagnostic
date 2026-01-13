package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.BranchLibrary;

import java.util.Optional;
import java.util.Set;

public interface BranchLibraryRepository extends JpaRepository<BranchLibrary, Long> {

    Set<BranchLibrary> findAllByOrganizationId(Long organizationId);

    @Query("select b.id from BranchLibrary b where b.fullName = ?1")
    Optional<Long> findIdByFullName(String fullName);

    boolean existsByFullName(String fullName);
}