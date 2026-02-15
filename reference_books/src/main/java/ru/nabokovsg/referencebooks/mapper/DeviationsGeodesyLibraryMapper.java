package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.NewDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.ResponseDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.UpdateDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.model.DeviationsGeodesyLibrary;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;
import ru.nabokovsg.referencebooks.model.RegulatoryDocumentationLibrary;

@Mapper(componentModel = "spring")
public interface DeviationsGeodesyLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "heatCarrier", ignore = true)
    @Mapping(target = "equipmentCondition", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "documentation", ignore = true)
    DeviationsGeodesyLibrary mapToDeviationsGeodesyLibrary(NewDeviationsGeodesyLibraryDto geodesyDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "heatCarrier", ignore = true)
    @Mapping(target = "equipmentCondition", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "documentation", ignore = true)
    void mapToUpdateDeviationsGeodesyLibrary(@MappingTarget DeviationsGeodesyLibrary deviationsGeodesy,
                                                            UpdateDeviationsGeodesyLibraryDto deviationsGeodesyDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "withHeatCarrier", ignore = true)
    @Mapping(target = "condition", ignore = true)
    @Mapping(target = "acceptablePrecipitation", ignore = true)
    @Mapping(target = "maxDifferenceNeighboringPoints", ignore = true)
    @Mapping(target = "maxDifferenceDiametricPoints", ignore = true)
    void mapWithFields(@MappingTarget DeviationsGeodesyLibrary deviationsGeodesy
                                     , EquipmentLibrary equipment
                                     , RegulatoryDocumentationLibrary documentation
                                     , String heatCarrier
                                     , String equipmentCondition);

    @Mapping(source = "deviations.equipment.equipmentFullName", target = "equipmentFullName")
    @Mapping(source = "deviations.documentation.document", target = "documentation")
    ResponseDeviationsGeodesyLibraryDto mapToResponseDeviationsGeodesyLibraryDto(DeviationsGeodesyLibrary deviations);
}