package ru.nabokovsg.equipmentunit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipmentunit.dto.client.*;
import ru.nabokovsg.equipmentunit.exceptions.BadRequestException;
import ru.nabokovsg.equipmentunit.exceptions.NotFoundException;
import ru.nabokovsg.equipmentunit.mapper.StructureOrganizationMapper;
import ru.nabokovsg.equipmentunit.model.StructureOrganization;
import ru.nabokovsg.equipmentunit.model_enum.BadRequestExceptionMassage;
import ru.nabokovsg.equipmentunit.model_enum.NotFoundExceptionMassage;
import ru.nabokovsg.equipmentunit.repository.StructureOrganizationRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class StructureOrganizationServiceImpl implements StructureOrganizationService {

    private final StructureOrganizationRepository repository;
    private final StructureOrganizationMapper mapper;

    @Override
    public void saveBranch(BranchLibraryDto branchDto) {
        Set<StructureOrganization> structures = repository.findAllByBranchId(branchDto.getId());
        if (structures.isEmpty()) {
            repository.save(mapper.mapBranchToStructureOrganization(branchDto));
        } else {
            structures.forEach(structure -> mapper.mapUpdateBranch(structure, branchDto));
            repository.saveAll(structures);
        }
    }

    @Override
    public void saveDepartment(DepartmentLibraryDto departmentDto) {
        Set<StructureOrganization> structures = repository.findAllByDepartmentId(departmentDto.getId());
        if (structures.isEmpty()) {
            repository.save(mapper.mapDepartmentToStructureOrganization(departmentDto));
        } else {
            structures.forEach(structure -> mapper.mapUpdateDepartment(structure, departmentDto));
            repository.saveAll(structures);
        }
    }

    @Override
    public void saveSource(HeatSupplySourceLibraryDto sourceDto) {
        Set<StructureOrganization> structures = repository.findAllByHeatSupplySourceId(sourceDto.getId());
        if (structures.isEmpty()) {
            repository.save(mapper.mapSourceToStructureOrganization(sourceDto));
        } else {
            structures.forEach(structure -> mapper.mapUpdateSource(structure, sourceDto));
            repository.saveAll(structures);
        }
    }

    @Override
    public void saveSite(HeatSupplySiteLibraryDto siteDto) {
        Set<StructureOrganization> structures = repository.findAllByHeatSupplySiteId(siteDto.getId());
        if (structures.isEmpty()) {
            repository.save(mapper.mapSiteToStructureOrganization(siteDto));
        } else {
            structures.forEach(structure -> mapper.mapUpdateHeatSupplySite(structure, siteDto));
            repository.saveAll(structures);
        }
    }

    @Override
    public void saveDevice(TechnicalDeviceLibraryDto deviceDto) {
        Set<StructureOrganization> structures = repository.findAllByTechnicalDeviceId(deviceDto.getId());
        if (structures.isEmpty()) {
            repository.save(mapper.mapDeviceToStructureOrganization(deviceDto));
        } else {
            structures.forEach(structure -> mapper.mapUpdateTechnicalDevice(structure, deviceDto));
            repository.saveAll(structures);
        }
    }

    @Override
    public StructureOrganization getStructureOrganization(Long sourceId, Long deviceId) {
        if (sourceId != null) {
            return repository.findByHeatSupplySourceId(sourceId)
                    .orElseThrow(() -> new NotFoundException(NotFoundExceptionMassage.SOURCE.label));
        }
        if (deviceId != null) {
            return repository.findByTechnicalDeviceId(deviceId)
                    .orElseThrow(() -> new NotFoundException(NotFoundExceptionMassage.TECHNICAL_DEVICE.label));
        }
        throw new BadRequestException(BadRequestExceptionMassage.NOT_STRUCTURE_ID.label);
    }
}