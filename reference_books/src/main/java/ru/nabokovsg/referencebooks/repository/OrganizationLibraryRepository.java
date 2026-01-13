package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.OrganizationLibrary;

import java.util.Set;

public interface OrganizationLibraryRepository extends JpaRepository<OrganizationLibrary, Long> {

    boolean existsByFullName(String fullName);

    @Query("select o from OrganizationLibrary o order by o.fullName")
    Set<OrganizationLibrary> findAllOrderByFullName();
}