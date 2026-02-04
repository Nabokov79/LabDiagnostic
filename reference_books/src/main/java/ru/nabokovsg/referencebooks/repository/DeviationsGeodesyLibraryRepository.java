package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.DeviationsGeodesyLibrary;

import java.util.Set;

public interface DeviationsGeodesyLibraryRepository extends JpaRepository<DeviationsGeodesyLibrary, Long> {

    boolean existsByEquipmentLibraryIdAndWithHeatCarrierAndCondition(Long equipmentLibraryId,
                                                                     boolean withHeatCarrier,
                                                                     boolean condition);

    @Query("select g.id " +
           "from DeviationsGeodesyLibrary g " +
           "where g.equipmentLibraryId=?1 and g.withHeatCarrier=?2 and g.condition=?3")
    Long findIdByEquipmentLibraryIdAndWithHeatCarrierAndCondition(Long equipmentLibraryId,
                                                                  boolean withHeatCarrier,
                                                                  boolean condition);

    @Query("select g " +
           "from DeviationsGeodesyLibrary g" +
          " order by g.equipmentLibrary")
    Set<DeviationsGeodesyLibrary> findAllOrderByEquipmentLibrary();
}