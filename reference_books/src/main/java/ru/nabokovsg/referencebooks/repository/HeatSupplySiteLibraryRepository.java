package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.HeatSupplySiteLibrary;

import java.util.Optional;
import java.util.Set;

public interface HeatSupplySiteLibraryRepository extends JpaRepository<HeatSupplySiteLibrary, Long> {

    boolean existsByFullDescription(String fullDescription);

    @Query("select h.id from HeatSupplySiteLibrary h where h.fullDescription = ?1")
    Optional<Long> findIdByFullDescription(String fullDescription);

    Set<HeatSupplySiteLibrary> findAllBySourceId(Long sourceId);
}