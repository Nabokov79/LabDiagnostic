package ru.nabokovsg.company.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.company.dto.heatSupplyArea.NewHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ResponseHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.ResponseShortHeatSupplyAreaDto;
import ru.nabokovsg.company.dto.heatSupplyArea.UpdateHeatSupplyAreaDto;
import ru.nabokovsg.company.model.Branch;
import ru.nabokovsg.company.model.HeatSupplyArea;

@Mapper(componentModel = "spring")
public interface HeatSupplyAreaMapper {

    HeatSupplyArea mapToHeatSupplyArea(NewHeatSupplyAreaDto areaDto);

    HeatSupplyArea mapToUpdateHeatSupplyArea(UpdateHeatSupplyAreaDto areaDto);


    @Mapping(source = "branch", target = "branch")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "shortName", ignore = true)
    HeatSupplyArea mapWithBranch(@MappingTarget HeatSupplyArea area, Branch branch);

    ResponseHeatSupplyAreaDto mapToFullHeatSupplyAreaDto(HeatSupplyArea heatSupplyArea);

    ResponseShortHeatSupplyAreaDto mapToShortHeatSupplyAreaDto(HeatSupplyArea heatSupplyArea);
}