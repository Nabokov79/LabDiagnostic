package ru.nabokovsg.company.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.company.dto.building.NewBuildingDto;
import ru.nabokovsg.company.dto.building.ResponseBuildingDto;
import ru.nabokovsg.company.dto.building.ResponseShortBuildingDto;
import ru.nabokovsg.company.dto.building.UpdateBuildingDto;
import ru.nabokovsg.company.model.Building;
import ru.nabokovsg.company.model.ExploitationRegion;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    Building mapToBuilding(NewBuildingDto buildingDto);

    Building mapToUpdateBuilding(UpdateBuildingDto buildingDto);

    @Mapping(source = "address", target = "address")
    @Mapping(source = "buildingType", target = "buildingType")
    @Mapping(source = "exploitationRegion", target = "exploitationRegion")
    @Mapping(target = "id", ignore = true)
    Building mapWitchData(@MappingTarget Building building
                                       , String buildingType
                                       , String address
                                       , ExploitationRegion exploitationRegion);

    ResponseBuildingDto mapToFullBuildingDto(Building building);

    ResponseShortBuildingDto mapToShortBuildingDto(Building building);
}