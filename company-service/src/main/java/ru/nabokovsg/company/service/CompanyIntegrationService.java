package ru.nabokovsg.company.service;

import ru.nabokovsg.company.dto.company.ResponseCompanyDto;
import ru.nabokovsg.company.dto.company.ResponseDocumentHeaderTemplateDto;

public interface CompanyIntegrationService {

    ResponseCompanyDto get(Long heatSupplyAreaId, Long exploitationRegionId, Long addressId);

    ResponseDocumentHeaderTemplateDto getByBranch(Long branchId);

   ResponseDocumentHeaderTemplateDto getByDepartment(Long departmentId);

    ResponseDocumentHeaderTemplateDto getByHeatSupplyArea(Long heatSupplyAreaId);

    ResponseDocumentHeaderTemplateDto getByExploitationRegion(Long exploitationRegionId);
}