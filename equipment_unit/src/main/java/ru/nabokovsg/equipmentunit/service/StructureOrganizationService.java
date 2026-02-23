package ru.nabokovsg.equipmentunit.service;

import ru.nabokovsg.equipmentunit.dto.client.*;
import ru.nabokovsg.equipmentunit.model.StructureOrganization;

public interface StructureOrganizationService {

    void saveBranch(BranchLibraryDto branchDto);

   void saveDepartment(DepartmentLibraryDto departmentDto);

    void saveSource(HeatSupplySourceLibraryDto sourceDto);

    void saveSite(HeatSupplySiteLibraryDto siteDto);

    void saveDevice(TechnicalDeviceLibraryDto deviceDto);

    StructureOrganization getStructureOrganization(Long sourceId, Long deviceId);
}