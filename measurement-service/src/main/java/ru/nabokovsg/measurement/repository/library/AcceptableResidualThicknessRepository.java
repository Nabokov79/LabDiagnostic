package ru.nabokovsg.measurement.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.library.AcceptableResidualThickness;

import java.util.Set;

public interface AcceptableResidualThicknessRepository extends JpaRepository<AcceptableResidualThickness, Long> {

    Set<AcceptableResidualThickness> findAllByEquipmentLibraryId(Long equipmentLibraryId);

    AcceptableResidualThickness findByEquipmentLibraryIdAndElementLibraryId(Long equipmentLibraryId
                                                                          , Long elementLibraryId);

    AcceptableResidualThickness findByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryId(
                                                                                            Long equipmentLibraryId
                                                                                          , Long elementLibraryId
                                                                                          , Long partElementLibraryId);
}