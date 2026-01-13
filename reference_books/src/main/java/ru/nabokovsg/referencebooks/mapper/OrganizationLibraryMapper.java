package ru.nabokovsg.referencebooks.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.NewOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.ResponseOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.ResponseShortOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.UpdateOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.model.OrganizationLibrary;

@Mapper(componentModel = "spring")
public interface OrganizationLibraryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branches", ignore = true)
    OrganizationLibrary mapToOrganization(NewOrganizationLibraryDto organizationDto);

    @Mapping(target = "branches", ignore = true)
    void mapToUpdateOrganization(@MappingTarget OrganizationLibrary organization, UpdateOrganizationLibraryDto organizationDto);

    ResponseOrganizationLibraryDto mapToOrganizationDto(OrganizationLibrary organization);

    ResponseShortOrganizationLibraryDto mapToShortOrganizationDto(OrganizationLibrary organization);
}