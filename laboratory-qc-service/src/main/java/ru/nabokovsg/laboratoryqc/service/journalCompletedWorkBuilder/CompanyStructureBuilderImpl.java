package ru.nabokovsg.laboratoryqc.service.journalCompletedWorkBuilder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryqc.client.ClientService;
import ru.nabokovsg.laboratoryqc.dto.companyStructureService.BuildingDto;
import ru.nabokovsg.laboratoryqc.dto.companyStructureService.CompanyStructureDto;
import ru.nabokovsg.laboratoryqc.mapper.CompanyStructureBuilderMapper;
import ru.nabokovsg.laboratoryqc.model.JournalCompletedWork;
import ru.nabokovsg.laboratoryqc.model.JournalType;

@Service
@RequiredArgsConstructor
public class CompanyStructureBuilderImpl implements CompanyStructureBuilder {

    private final ClientService client;
    private final CompanyStructureBuilderMapper mapper;

    @Override
    public void build(JournalCompletedWork journal, JournalType journalType) {
        CompanyStructureDto structure = client.getStructuralDivision(journal.getHeatSupplyAreaId()
                                                                   , journal.getExploitationRegionId()
                                                                   , journal.getAddressId()
                                                                   , journalType);
        mapper.mapToCompanyStructure(journal, structure, getAddress(structure));
    }

    private String getAddress(CompanyStructureDto companyStructure) {
        if (companyStructure.getBuilding() == null) {
            return companyStructure.getAddress();
        }
        return String.join(", ", buildBuilding(companyStructure.getBuilding()), companyStructure.getAddress());
    }

    private String buildBuilding(BuildingDto building) {
        String workPlace = building.getBuildingType();
        if (building.getLogin() != null) {
            workPlace = String.join(" ", workPlace, "«", building.getLogin(), "»");
        }
        return workPlace;
    }
}