package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.NewDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.ResponseDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.dto.deviationsGeodesyLibrary.UpdateDeviationsGeodesyLibraryDto;
import ru.nabokovsg.referencebooks.model.DeviationsGeodesyLibrary;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;

@Mapper(componentModel = "spring")
public interface DeviationsGeodesyLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibrary", ignore = true)
    @Mapping(target = "volume", ignore = true)
    @Mapping(target = "heatCarrier", ignore = true)
    @Mapping(target = "equipmentCondition", ignore = true)
    DeviationsGeodesyLibrary mapToAcceptableDeviationsGeodesy(NewDeviationsGeodesyLibraryDto geodesyDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibrary", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "volume", ignore = true)
    @Mapping(target = "heatCarrier", ignore = true)
    @Mapping(target = "equipmentCondition", ignore = true)
    void mapToUpdateAcceptableDeviationsGeodesy(@MappingTarget DeviationsGeodesyLibrary deviationsGeodesy,
                                                                UpdateDeviationsGeodesyLibraryDto deviationsGeodesyDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "withHeatCarrier", ignore = true)
    @Mapping(target = "condition", ignore = true)
    @Mapping(target = "acceptablePrecipitation", ignore = true)
    @Mapping(target = "maxDifferenceNeighboringPoints", ignore = true)
    @Mapping(target = "maxDifferenceDiametricPoints", ignore = true)
    void mapWithFields(@MappingTarget DeviationsGeodesyLibrary deviationsGeodesy, String equipmentLibrary
                                                                                , Integer volume
                                                                                , String heatCarrier
                                                                                , String equipmentCondition);

    ResponseDeviationsGeodesyLibraryDto mapToResponseAcceptableDeviationsGeodesyDto(
                                                                                   DeviationsGeodesyLibrary deviations);
}