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
    DeviationsGeodesyLibrary mapToAcceptableDeviationsGeodesy(NewDeviationsGeodesyLibraryDto deviationsGeodesyDto
                                                                      , String heatCarrier
                                                                      , String equipmentCondition
                                                                      , EquipmentLibrary equipment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipmentLibraryId", ignore = true)
    @Mapping(target = "heatCarrier", ignore = true)
    @Mapping(target = "equipmentCondition", ignore = true)
    @Mapping(target = "volume", ignore = true)
    void mapToUpdateAcceptableDeviationsGeodesy(@MappingTarget DeviationsGeodesyLibrary deviationsGeodesy,
                                                                UpdateDeviationsGeodesyLibraryDto deviationsGeodesyDto);

    ResponseDeviationsGeodesyLibraryDto mapToResponseAcceptableDeviationsGeodesyDto(
                                                                            DeviationsGeodesyLibrary deviationsGeodesy);
}