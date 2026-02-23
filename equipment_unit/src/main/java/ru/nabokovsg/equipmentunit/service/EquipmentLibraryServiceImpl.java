package ru.nabokovsg.equipmentunit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipmentunit.dto.client.ElementLibraryDto;
import ru.nabokovsg.equipmentunit.dto.client.EquipmentLibraryDto;
import ru.nabokovsg.equipmentunit.dto.client.PartElementLibraryDto;
import ru.nabokovsg.equipmentunit.mapper.EquipmentLibraryMapper;
import ru.nabokovsg.equipmentunit.model.Element;
import ru.nabokovsg.equipmentunit.model.EquipmentUnit;
import ru.nabokovsg.equipmentunit.model.PartElement;
import ru.nabokovsg.equipmentunit.repository.ElementRepository;
import ru.nabokovsg.equipmentunit.repository.EquipmentUnitRepository;
import ru.nabokovsg.equipmentunit.repository.PartElementRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EquipmentLibraryServiceImpl implements EquipmentLibraryService {

    private final EquipmentLibraryMapper mapper;
    private final EquipmentUnitRepository equipmentRepository;
    private final ElementRepository elementRepository;
    private final PartElementRepository partElementRepository;

    @Override
    public void updateEquipment(EquipmentLibraryDto equipmentDto) {
        Set<EquipmentUnit> equipments = equipmentRepository.findAllByEquipmentLibraryId(equipmentDto.getId());
        if (!equipments.isEmpty()) {
            equipments.forEach(equipmentUnit ->
                    mapper.mapUpdateEquipmentUnitName(equipmentUnit, equipmentDto.getFullName()));
        }
        equipmentRepository.saveAll(equipments);
    }

    @Override
    public void updateElement(ElementLibraryDto elementLibraryDto) {
        Set<Element> elements = elementRepository.findAllByElementLibraryId(elementLibraryDto.getId());
        if (!elements.isEmpty()) {
            elements.forEach(element ->
                    mapper.mapUpdateElementName(element, elementLibraryDto.getName()));
        }
        elementRepository.saveAll(elements);
    }

    @Override
    public void updatePartElement(PartElementLibraryDto partElementLibraryDto) {
        Set<PartElement> partsElements = partElementRepository.findAllByPartElementLibraryId(partElementLibraryDto.getId());
        if (!partsElements.isEmpty()) {
            partsElements.forEach(partElement ->
                    mapper.mapUpdatePartElementName(partElement, partElementLibraryDto.getName()));
        }
        partElementRepository.saveAll(partsElements);
    }
}