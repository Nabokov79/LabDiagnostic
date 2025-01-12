package ru.nabokovsg.equipment.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.equipment.model.library.PartElementLibrary;

import java.util.Set;

public interface PartElementLibraryRepository extends JpaRepository<PartElementLibrary, Long> {

    boolean existsByElementLibraryIdAndPartElementName(Long elementLibraryId, String partElementName);

    Set<PartElementLibrary> findAllByElementLibraryId(Long elementLibraryId);
}