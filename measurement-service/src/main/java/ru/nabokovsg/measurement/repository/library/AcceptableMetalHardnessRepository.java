package ru.nabokovsg.measurement.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.library.AcceptableMetalHardness;

import java.util.Set;

public interface AcceptableMetalHardnessRepository extends JpaRepository<AcceptableMetalHardness, Long> {

    Set<AcceptableMetalHardness> findAllByEquipmentLibraryId(Long equipmentLibraryId);

    AcceptableMetalHardness findByEquipmentLibraryIdAndElementLibraryId(Long equipmentLibraryId
                                                                      , Long elementLibraryId);

    AcceptableMetalHardness findByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryId(
                                                                                          Long equipmentLibraryId
                                                                                        , Long elementLibraryId
                                                                                        , Long partElementLibraryId);
}