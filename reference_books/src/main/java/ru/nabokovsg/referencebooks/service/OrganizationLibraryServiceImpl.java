package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.NewOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.ResponseOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.ResponseShortOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.dto.organizationLibrary.UpdateOrganizationLibraryDto;
import ru.nabokovsg.referencebooks.model.OrganizationLibrary;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.OrganizationLibraryMapper;
import ru.nabokovsg.referencebooks.model_enum.BadRequestExceptionMassage;
import ru.nabokovsg.referencebooks.model_enum.NotFoundExceptionMassage;
import ru.nabokovsg.referencebooks.repository.OrganizationLibraryRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationLibraryServiceImpl implements OrganizationLibraryService {

    private final OrganizationLibraryRepository repository;
    private final OrganizationLibraryMapper mapper;

    @Override
    public ResponseShortOrganizationLibraryDto save(NewOrganizationLibraryDto organizationDto) {
        validateByFullName(organizationDto.getFullName());
        return mapper.mapToShortOrganizationDto(repository.save(mapper.mapToOrganization(organizationDto)));
    }

    @Override
    public ResponseShortOrganizationLibraryDto update(UpdateOrganizationLibraryDto organizationDto) {
        validateByFullName(organizationDto.getFullName());
        OrganizationLibrary organization = getById(organizationDto.getId());
        mapper.mapToUpdateOrganization(organization, organizationDto);
        return mapper.mapToShortOrganizationDto(repository.save(organization));
    }

    @Override
    public ResponseOrganizationLibraryDto get(Long id) {
        return mapper.mapToOrganizationDto(getById(id));
    }

    @Override
    public List<ResponseShortOrganizationLibraryDto> getAll(String name) {
        Set<OrganizationLibrary> organizations = repository.findAllOrderByFullName();
        if (name != null) {
            String organizationName = name.toLowerCase();
            organizations = organizations.stream()
                             .filter(organization -> organization.getFullName().toLowerCase().contains(organizationName)
                                                || organization.getShortName().toLowerCase().contains(organizationName))
                             .collect(Collectors.toSet());
        }
        return organizations.stream()
                            .map(mapper::mapToShortOrganizationDto)
                            .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(NotFoundExceptionMassage.ORGANIZATION.label);
    }

    @Override
    public OrganizationLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundExceptionMassage.ORGANIZATION.label));
    }

    private void validateByFullName(String fullName) {
        if (repository.existsByFullName(fullName)) {
            throw new BadRequestException(
                    String.join("", BadRequestExceptionMassage.DUPLICATE.label, fullName));
        }
    }
}