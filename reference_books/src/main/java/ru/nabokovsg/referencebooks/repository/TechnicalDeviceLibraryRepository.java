package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.TechnicalDevice;

import java.util.Optional;
import java.util.Set;

public interface TechnicalDeviceLibraryRepository extends JpaRepository<TechnicalDevice, Long> {

    Set<TechnicalDevice> findAllBySiteId(Long siteId);

    boolean existsByFullName(String fullName);

    @Query("select d.id from TechnicalDevice d where d.fullName = ?1")
    Optional<Long> findIdByFullName(String fullName);
}