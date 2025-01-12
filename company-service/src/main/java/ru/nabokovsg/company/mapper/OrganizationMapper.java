package ru.nabokovsg.company.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.company.dto.organization.NewOrganizationDto;
import ru.nabokovsg.company.dto.organization.ResponseOrganizationDto;
import ru.nabokovsg.company.dto.organization.ResponseShortOrganizationDto;
import ru.nabokovsg.company.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.company.model.Organization;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    Organization mapToOrganization(NewOrganizationDto organizationDto);

    Organization mapToUpdateOrganization(UpdateOrganizationDto organizationDto);

    @Mapping(target = "id", ignore = true)
    Organization mapTWithData(@MappingTarget Organization organization, String address);

    ResponseOrganizationDto mapToOrganizationDto(Organization organization);

    ResponseShortOrganizationDto mapToShortOrganizationDto(Organization organization);
}