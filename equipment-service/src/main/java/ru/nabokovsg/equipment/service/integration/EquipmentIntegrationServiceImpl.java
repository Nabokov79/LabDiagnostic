package ru.nabokovsg.equipment.service.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.integration.EquipmentDto;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mapper.integration.EquipmentIntegrationMapper;
import ru.nabokovsg.equipment.repository.equipment.ElementRepository;
import ru.nabokovsg.equipment.repository.equipment.EquipmentRepository;
import ru.nabokovsg.equipment.repository.equipment.PartElementRepository;

@Service
@RequiredArgsConstructor
public class EquipmentIntegrationServiceImpl implements EquipmentIntegrationService {

    private final EquipmentRepository equipmentRepository;
    private final ElementRepository elementRepository;
    private final PartElementRepository partElementRepository;
    private final EquipmentIntegrationMapper mapper;

    @Override
    public EquipmentDto getIds(Long elementId, Long partElementId) {
        if (partElementId != null) {
            return mapper.mapToPartElement(
                    partElementRepository.findById(partElementId)
                            .orElseThrow(() -> new NotFoundException(
                                    String.format("EquipmentPartElement with id=%s not found", partElementId))));
        }
        return mapper.mapToElement(elementRepository.findById(elementId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Equipment element with id=%s not found", elementId))));
    }

    @Override
    public EquipmentDto getByEquipmentId(Long id) {
        return mapper.mapToEquipment(equipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Equipment with id=%s not found", id))));
    }
}