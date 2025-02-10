package ru.nabokovsg.measurement.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.library.AcceptableMetalHardness;

import java.util.Optional;
import java.util.Set;

public interface AcceptableMetalHardnessRepository extends JpaRepository<AcceptableMetalHardness, Long> {

    Set<AcceptableMetalHardness> findAllByEquipmentLibraryId(Long equipmentLibraryId);

    boolean existsByEquipmentLibraryIdAndElementLibraryIdAndStandardSize(Long equipmentLibraryId
                                                                       , Long elementLibraryId
                                                                       , String standardSize);

    boolean existsByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryIdAndStandardSize(
                                                                                             Long equipmentLibraryId
                                                                                           , Long elementLibraryId
                                                                                           , Long partElementLibraryId
                                                                                           , String standardSize);

    Optional<AcceptableMetalHardness> findByEquipmentLibraryIdAndElementLibraryId(Long equipmentLibraryId
                                                                      , Long elementLibraryId);

    Optional<AcceptableMetalHardness> findByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryId(
                                                                                          Long equipmentLibraryId
                                                                                        , Long elementLibraryId
                                                                                        , Long partElementLibraryId);
}