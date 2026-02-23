package ru.nabokovsg.equipmentunit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.equipmentunit.dto.client.*;
import ru.nabokovsg.equipmentunit.model.StructureOrganization;

@Mapper(componentModel = "spring")
public interface StructureOrganizationMapper {

    @Mapping(source = "id", target = "branchId")
    @Mapping(source = "fullName", target = "branch")
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "heatSupplySiteId", ignore = true)
    @Mapping(target = "heatSupplySite", ignore = true)
    @Mapping(target = "technicalDeviceId", ignore = true)
    @Mapping(target = "technicalDevice", ignore = true)
    @Mapping(target = "heatSupplySourceId", ignore = true)
    @Mapping(target = "heatSupplySource", ignore = true)
    StructureOrganization mapBranchToStructureOrganization(BranchLibraryDto branchDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    @Mapping(source = "branchDto.fullName", target = "branch")
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "heatSupplySiteId", ignore = true)
    @Mapping(target = "heatSupplySite", ignore = true)
    @Mapping(target = "technicalDeviceId", ignore = true)
    @Mapping(target = "technicalDevice", ignore = true)
    @Mapping(target = "heatSupplySourceId", ignore = true)
    @Mapping(target = "heatSupplySource", ignore = true)
    void mapUpdateBranch(@MappingTarget StructureOrganization structure, BranchLibraryDto branchDto);

    @Mapping(source = "id", target = "departmentId")
    @Mapping(source = "fullName", target = "department")
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "heatSupplySiteId", ignore = true)
    @Mapping(target = "heatSupplySite", ignore = true)
    @Mapping(target = "technicalDeviceId", ignore = true)
    @Mapping(target = "technicalDevice", ignore = true)
    @Mapping(target = "heatSupplySourceId", ignore = true)
    @Mapping(target = "heatSupplySource", ignore = true)
    StructureOrganization mapDepartmentToStructureOrganization(DepartmentLibraryDto departmentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "heatSupplySiteId", ignore = true)
    @Mapping(target = "heatSupplySite", ignore = true)
    @Mapping(target = "technicalDeviceId", ignore = true)
    @Mapping(target = "technicalDevice", ignore = true)
    @Mapping(target = "heatSupplySourceId", ignore = true)
    @Mapping(target = "heatSupplySource", ignore = true)
    @Mapping(source = "departmentDto.fullName", target = "department")
    void mapUpdateDepartment(@MappingTarget StructureOrganization structure, DepartmentLibraryDto departmentDto);

    @Mapping(source = "id", target = "heatSupplySiteId")
    @Mapping(source = "fullDescription", target = "heatSupplySite")
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "technicalDeviceId", ignore = true)
    @Mapping(target = "technicalDevice", ignore = true)
    @Mapping(target = "heatSupplySourceId", ignore = true)
    @Mapping(target = "heatSupplySource", ignore = true)
    StructureOrganization mapSiteToStructureOrganization(HeatSupplySiteLibraryDto siteDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "heatSupplySiteId", ignore = true)
    @Mapping(source = "siteDto.fullDescription", target = "heatSupplySite")
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "technicalDeviceId", ignore = true)
    @Mapping(target = "technicalDevice", ignore = true)
    @Mapping(target = "heatSupplySourceId", ignore = true)
    @Mapping(target = "heatSupplySource", ignore = true)
    void mapUpdateHeatSupplySite(@MappingTarget StructureOrganization structure, HeatSupplySiteLibraryDto siteDto);

    @Mapping(source = "id", target = "technicalDeviceId")
    @Mapping(source = "fullName", target = "technicalDevice")
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "heatSupplySiteId", ignore = true)
    @Mapping(target = "heatSupplySite", ignore = true)
    @Mapping(target = "heatSupplySourceId", ignore = true)
    @Mapping(target = "heatSupplySource", ignore = true)
    StructureOrganization mapDeviceToStructureOrganization(TechnicalDeviceLibraryDto deviceDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "technicalDeviceId", ignore = true)
    @Mapping(source = "deviceDto.fullName", target = "technicalDevice")
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "heatSupplySiteId", ignore = true)
    @Mapping(target = "heatSupplySite", ignore = true)
    @Mapping(target = "heatSupplySourceId", ignore = true)
    @Mapping(target = "heatSupplySource", ignore = true)
    void mapUpdateTechnicalDevice(@MappingTarget StructureOrganization structure, TechnicalDeviceLibraryDto deviceDto);

    @Mapping(source = "id", target = "heatSupplySourceId")
    @Mapping(source = "source", target = "heatSupplySource")
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "heatSupplySiteId", ignore = true)
    @Mapping(target = "heatSupplySite", ignore = true)
    @Mapping(target = "technicalDeviceId", ignore = true)
    @Mapping(target = "technicalDevice", ignore = true)
    StructureOrganization mapSourceToStructureOrganization(HeatSupplySourceLibraryDto sourceDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "heatSupplySourceId", ignore = true)
    @Mapping(source = "sourceDto.source", target = "heatSupplySource")
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "heatSupplySiteId", ignore = true)
    @Mapping(target = "heatSupplySite", ignore = true)
    @Mapping(target = "technicalDeviceId", ignore = true)
    @Mapping(target = "technicalDevice", ignore = true)
    void mapUpdateSource(@MappingTarget StructureOrganization structure, HeatSupplySourceLibraryDto sourceDto);
}