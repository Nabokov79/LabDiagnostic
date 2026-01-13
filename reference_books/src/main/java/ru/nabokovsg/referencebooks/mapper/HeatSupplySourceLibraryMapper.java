package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.NewHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.ResponseHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.ResponseShortHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySourceLibrary.UpdateHeatSupplySourceLibraryDto;
import ru.nabokovsg.referencebooks.model.DepartmentLibrary;
import ru.nabokovsg.referencebooks.model.HeatSupplySourceLibrary;

@Mapper(componentModel = "spring")
public interface HeatSupplySourceLibraryMapper {

    @Mapping(source = "department", target = "department")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "heatSupplySites", ignore = true)
    HeatSupplySourceLibrary mapToHeatSupplySource(NewHeatSupplySourceLibraryDto sourceDto
                                                , DepartmentLibrary department);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "heatSupplySites", ignore = true)
    void mapToUpdateHeatSupplySource(@MappingTarget HeatSupplySourceLibrary heatSupplySource
                                                  , UpdateHeatSupplySourceLibraryDto sourceDto);

    ResponseHeatSupplySourceLibraryDto mapToResponseHeatSupplySourceDto(HeatSupplySourceLibrary heatSupplySource);

    ResponseShortHeatSupplySourceLibraryDto mapToResponseShortHeatSupplySourceLibraryDto(
                                                                             HeatSupplySourceLibrary heatSupplySource);
}