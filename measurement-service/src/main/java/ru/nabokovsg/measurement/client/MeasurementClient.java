package ru.nabokovsg.measurement.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.nabokovsg.measurement.dto.client.EquipmentDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementClient {
    private final EquipmentClient equipmentClient;
    private static final String DELIMITER = "/";
    private static final String API_PREFIX_EQUIPMENT = "/equipment/integration";

    public EquipmentDto getEquipment(Long elementId, Long partElementId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("elementId", List.of(String.valueOf(elementId)));
        if (partElementId != null) {
            params.put("partElementId", List.of(String.valueOf(partElementId)));
        }
        return equipmentClient.getEquipment(API_PREFIX_EQUIPMENT, params);
    }

    public EquipmentDto getEquipment(Long equipmentId) {
        return equipmentClient.getEquipment(String.join(DELIMITER, API_PREFIX_EQUIPMENT, String.valueOf(equipmentId)));
    }
}