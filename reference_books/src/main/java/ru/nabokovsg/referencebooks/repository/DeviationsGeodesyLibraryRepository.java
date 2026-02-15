package ru.nabokovsg.referencebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.referencebooks.model.DeviationsGeodesyLibrary;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;

import java.util.Set;

public interface DeviationsGeodesyLibraryRepository extends JpaRepository<DeviationsGeodesyLibrary, Long> {

    boolean existsByEquipmentAndDocumentationAndWithHeatCarrierAndCondition(EquipmentLibrary equipment
                                                                          , RegulatoryDocumentationLibrary documentation
                                                                          , boolean withHeatCarrier
                                                                          , boolean condition);

    @Query("select g.id " +
           "from DeviationsGeodesyLibrary g " +
           "where g.equipment=?1 and g.documentation=?2 and g.withHeatCarrier=?3 and g.condition=?4")
    Long findIdByEquipmentAndDocumentationAndWithHeatCarrierAndCondition(EquipmentLibrary equipment
            , RegulatoryDocumentationLibrary documentation
            , boolean withHeatCarrier
            , boolean condition);

    @Query("select g " +
           "from DeviationsGeodesyLibrary g" +
          " order by g.equipment.equipmentFullName")
    Set<DeviationsGeodesyLibrary> findAllOrderByEquipmentLibrary();
}