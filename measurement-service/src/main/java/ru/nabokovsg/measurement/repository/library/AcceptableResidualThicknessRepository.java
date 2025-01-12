package ru.nabokovsg.measurement.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.measurement.model.library.AcceptableResidualThickness;

import java.util.Set;

public interface AcceptableResidualThicknessRepository extends JpaRepository<AcceptableResidualThickness, Long> {

    Set<AcceptableResidualThickness> findAllByEquipmentLibraryId(Long equipmentLibraryId);

    boolean existsByEquipmentLibraryIdAndElementLibraryIdAndStandardSize(Long equipmentLibraryId
                                                                       , Long elementLibraryId
                                                                       , String standardSize);

    boolean existsByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryIdAndStandardSize(
                                                                                           Long equipmentLibraryId
                                                                                         , Long elementLibraryId
                                                                                         , Long partElementLibraryId
                                                                                         , String standardSize);

    AcceptableResidualThickness findByEquipmentLibraryIdAndElementLibraryIdAndStandardSize(Long equipmentLibraryId
                                                                                         , Long elementLibraryId
                                                                                         , String standardSize);

    AcceptableResidualThickness findByEquipmentLibraryIdAndElementLibraryIdAndPartElementLibraryIdAndStandardSize(
                                                                                              Long equipmentLibraryId
                                                                                            , Long elementLibraryId
                                                                                            , Long partElementLibraryId
                                                                                            , String standardSize);
}