package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.NewTechnicalDeviceLibraryDto;
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.ResponseTechnicalDeviceLibraryDto;
import ru.nabokovsg.referencebooks.dto.technicalDeviceLibrary.UpdateTechnicalDeviceLibraryDto;
import ru.nabokovsg.referencebooks.model.HeatSupplySiteLibrary;
import ru.nabokovsg.referencebooks.model.TechnicalDevice;

@Mapper(componentModel = "spring")
public interface TechnicalDeviceLibraryMapper {

    @Mapping(target = "id", ignore = true)
    TechnicalDevice mapToTechnicalDevice(NewTechnicalDeviceLibraryDto deviceDto, HeatSupplySiteLibrary site);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "site", ignore = true)
    void mapToUpdateTechnicalDevice(@MappingTarget TechnicalDevice device, UpdateTechnicalDeviceLibraryDto deviceDto);

    ResponseTechnicalDeviceLibraryDto mapToResponseTechnicalDeviceLibraryDto(TechnicalDevice device);
}