package ru.nabokovsg.equipment.service.integration;

import ru.nabokovsg.equipment.dto.integration.EquipmentDto;

public interface EquipmentIntegrationService {

    EquipmentDto getIds(Long elementId, Long partElementId);

    EquipmentDto getByEquipmentId(Long id);
}