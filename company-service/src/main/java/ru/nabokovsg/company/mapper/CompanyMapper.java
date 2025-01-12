package ru.nabokovsg.company.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.company.dto.company.ResponseCompanyDto;
import ru.nabokovsg.company.dto.company.ResponseDocumentHeaderTemplateDto;
import ru.nabokovsg.company.model.*;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(source = "exploitationRegion.branch.fullName", target = "branchFullName")
    @Mapping(source = "exploitationRegion.branch.shortName", target = "branchShortName")
    @Mapping(source = "building", target = "building")
    @Mapping(source = "exploitationRegion", target = "exploitationRegion")
    @Mapping(target = "heatSupplyArea", ignore = true)
    @Mapping(target = "address", ignore = true)
    ResponseCompanyDto mapExploitationRegion(ExploitationRegion exploitationRegion, Building building);

    @Mapping(source = "heatSupplyArea.branch.fullName", target = "branchFullName")
    @Mapping(source = "heatSupplyArea.branch.shortName", target = "branchShortName")
    @Mapping(source = "heatSupplyArea", target = "heatSupplyArea")
    @Mapping(source = "address", target = "address")
    @Mapping(target = "exploitationRegion", ignore = true)
    @Mapping(target = "building", ignore = true)
    ResponseCompanyDto mapHeatSupplyArea(HeatSupplyArea heatSupplyArea, String address);

    @Mapping(source = "branch.organization.fullName", target = "organizationFullName")
    @Mapping(source = "branch.organization.shortName", target = "organizationShortName")
    @Mapping(source = "branch.fullName", target = "branchFullName")
    @Mapping(source = "branch.shortName", target = "branchShortName")
    ResponseDocumentHeaderTemplateDto mapBranch(Branch branch);

    @Mapping(source = "department.branch.organization.fullName", target = "organizationFullName")
    @Mapping(source = "department.branch.organization.shortName", target = "organizationShortName")
    @Mapping(source = "department.branch.fullName", target = "branchFullName")
    @Mapping(source = "department.branch.shortName", target = "branchShortName")
    @Mapping(source = "department.fullName", target = "departmentFullName")
    @Mapping(source = "department.shortName", target = "departmentShortName")
    ResponseDocumentHeaderTemplateDto mapDepartment(Department department);

    @Mapping(source = "heatSupplyArea.branch.organization.fullName", target = "organizationFullName")
    @Mapping(source = "heatSupplyArea.branch.organization.shortName", target = "organizationShortName")
    @Mapping(source = "heatSupplyArea.branch.fullName", target = "branchFullName")
    @Mapping(source = "heatSupplyArea.branch.shortName", target = "branchShortName")
    @Mapping(source = "heatSupplyArea.fullName", target = "heatSupplyAreaFullName")
    @Mapping(source = "heatSupplyArea.shortName", target = "heatSupplyAreaShortName")
    ResponseDocumentHeaderTemplateDto mapHeatSupplyArea(HeatSupplyArea heatSupplyArea);

    @Mapping(source = "exploitationRegion.branch.organization.fullName", target = "organizationFullName")
    @Mapping(source = "exploitationRegion.branch.organization.shortName", target = "organizationShortName")
    @Mapping(source = "exploitationRegion.branch.fullName", target = "branchFullName")
    @Mapping(source = "exploitationRegion.branch.shortName", target = "branchShortName")
    @Mapping(source = "exploitationRegion.fullName", target = "exploitationRegionFullName")
    @Mapping(source = "exploitationRegion.shortName", target = "exploitationRegionShortName")
    ResponseDocumentHeaderTemplateDto mapExploitationRegion(ExploitationRegion exploitationRegion);
}