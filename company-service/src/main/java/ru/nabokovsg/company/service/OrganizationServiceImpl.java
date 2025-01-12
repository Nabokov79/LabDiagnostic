package ru.nabokovsg.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.company.dto.organization.NewOrganizationDto;
import ru.nabokovsg.company.dto.organization.ResponseOrganizationDto;
import ru.nabokovsg.company.dto.organization.ResponseShortOrganizationDto;
import ru.nabokovsg.company.dto.organization.UpdateOrganizationDto;
import ru.nabokovsg.company.exceptions.BadRequestException;
import ru.nabokovsg.company.exceptions.NotFoundException;
import ru.nabokovsg.company.mapper.OrganizationMapper;
import ru.nabokovsg.company.model.Organization;
import ru.nabokovsg.company.repository.OrganizationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper mapper;
    private final AddressService addressService;

    @Override
    public ResponseShortOrganizationDto save(NewOrganizationDto organizationDto) {
        if (repository.existsByFullName(organizationDto.getFullName())) {
            throw new BadRequestException(
                    String.format("Organization with full name=%s is found", organizationDto.getFullName())
            );
        }
        return mapper.mapToShortOrganizationDto(
                repository.save(map(
                        mapper.mapToOrganization(organizationDto), organizationDto.getAddressId()))
        );
    }

    @Override
    public ResponseShortOrganizationDto update(UpdateOrganizationDto organizationDto) {
        if (repository.existsById(organizationDto.getId())) {
            return mapper.mapToShortOrganizationDto(
                    repository.save(map(
                            mapper.mapToUpdateOrganization(organizationDto), organizationDto.getAddressId()))
            );
        }
        throw new NotFoundException(
                String.format("Organization with name=%s not found for update.", organizationDto.getFullName()));
    }

    @Override
    public ResponseOrganizationDto get(Long id) {
        return mapper.mapToOrganizationDto(getById(id));
    }

    @Override
    public List<ResponseShortOrganizationDto> getAll() {
        return repository.findAll().stream()
                                   .map(mapper::mapToShortOrganizationDto)
                                   .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
       throw new NotFoundException(String.format("Organization with id=%s not found for delete.", id));
    }

    @Override
    public Organization getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(
                                                    String.format("Organization with id=%s not found",id)));
    }

    private Organization map(Organization organization, Long addressId) {
        return mapper.mapTWithData(organization, addressService.getAddressString(addressId));
    }
}