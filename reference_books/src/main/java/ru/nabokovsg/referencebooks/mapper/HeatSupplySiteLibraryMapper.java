package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.NewHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.ResponseHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.ResponseShortHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.dto.heatSupplySiteLibrary.UpdateHeatSupplySiteLibraryDto;
import ru.nabokovsg.referencebooks.model.HeatSupplySiteLibrary;
import ru.nabokovsg.referencebooks.model.HeatSupplySourceLibrary;

@Mapper(componentModel = "spring")
public interface HeatSupplySiteLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "devices", ignore = true)
    @Mapping(source = "source", target = "source")
    HeatSupplySiteLibrary mapToHeatSupplySite(NewHeatSupplySiteLibraryDto heatSupplySite, HeatSupplySourceLibrary source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "devices", ignore = true)
    @Mapping(target = "source", ignore = true)
    void mapToUpdateHeatSupplySite(@MappingTarget HeatSupplySiteLibrary heatSupplySite
                                                , UpdateHeatSupplySiteLibraryDto regionDto);

    ResponseHeatSupplySiteLibraryDto mapToResponseHeatSupplySiteDto(HeatSupplySiteLibrary heatSupplySite);

    ResponseShortHeatSupplySiteLibraryDto mapToResponseShortHeatSupplySiteDto(HeatSupplySiteLibrary heatSupplySite);
}