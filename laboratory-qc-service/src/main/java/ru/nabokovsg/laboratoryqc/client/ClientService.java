package ru.nabokovsg.laboratoryqc.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.nabokovsg.laboratoryqc.dto.companyStructureService.CompanyStructureDto;
import ru.nabokovsg.laboratoryqc.dto.equipmentDiagnosedQCLService.EquipmentDto;
import ru.nabokovsg.laboratoryqc.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryqc.model.JournalType;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientService {

    private final CompanyStructureClient companyStructureClient;
    private final EquipmentClient equipmentClient;

    public CompanyStructureDto getStructuralDivision(Long heatSupplyAreaId, Long exploitationRegionId, Long addressId, JournalType journalType) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        switch (journalType) {
            case EQUIPMENT ->
                params.put("exploitationRegionId", List.of(String.valueOf(exploitationRegionId)));
            case HEAT_SUPPLY ->
                params.put("heatSupplyAreaId", List.of(String.valueOf(heatSupplyAreaId)));
            case QUALITY_CONTROL -> {
                params.put("exploitationRegionId", List.of(String.valueOf(exploitationRegionId)));
                params.put("heatSupplyAreaId", List.of(String.valueOf(heatSupplyAreaId)));
            }
            default -> throw new BadRequestException(String.format("Journal type=%s is not supported", journalType));
        }
        params.put("addressId", List.of(String.valueOf(addressId)));
        return companyStructureClient.get(params);
    }

    public EquipmentDto getEquipment(Long equipmentId, Long elementId, JournalType journalType) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("equipmentId", List.of(String.valueOf(equipmentId)));
        switch (journalType) {
            case EQUIPMENT ->
                params.put("library", List.of(String.valueOf(false)));
            case QUALITY_CONTROL, HEAT_SUPPLY -> {
                if (elementId != null) {
                    params.put("elementId", List.of(String.valueOf(elementId)));
                }
                params.put("library", List.of(String.valueOf(true)));
            }
            default -> throw new BadRequestException(String.format("Journal type=%s is not supported", journalType));
        }
        return equipmentClient.get(params);
    }
}