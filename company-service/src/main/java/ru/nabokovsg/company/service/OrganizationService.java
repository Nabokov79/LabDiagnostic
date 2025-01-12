package ru.nabokovsg.company.service;

import ru.nabokovsg.company.dto.organization.NewOrganizationDto;
import ru.nabokovsg.company.dto.organization.ResponseOrganizationDto;
import ru.nabokovsg.company.dto.organization.ResponseShortOrganizationDto;
import ru.nabokovsg.company.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.company.model.Organization;

import java.util.List;

public interface OrganizationService {

    ResponseShortOrganizationDto save(NewOrganizationDto organizationDto);

    ResponseShortOrganizationDto update(UpdateOrganizationDto organizationDto);

    ResponseOrganizationDto get(Long id);

    Organization getById(Long id);

    List<ResponseShortOrganizationDto> getAll();

    void delete(Long id);
}