package ru.nabokovsg.referencebooks.service;

import ru.nabokovsg.referencebooks.dto.organizationLibrary.NewOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.ResponseOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.ResponseShortOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.UpdateOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.model.OrganizationLibrary;

import java.util.List;

public interface OrganizationLibraryService {

    ResponseShortOrganizationLibraryDto save(NewOrganizationLibraryDto organizationDto);

   ResponseShortOrganizationLibraryDto update(UpdateOrganizationLibraryDto organizationDto);

    ResponseOrganizationLibraryDto get(Long id);

    List<ResponseShortOrganizationLibraryDto> getAll(String name);

    void delete(Long id);

    OrganizationLibrary getById(Long id);
}