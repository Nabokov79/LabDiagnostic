package ru.nabokovsg.equipment.service.synchronizing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.mapper.library.SynchronizingMapper;
import ru.nabokovsg.equipment.model.equipment.Element;
import ru.nabokovsg.equipment.model.equipment.Equipment;
import ru.nabokovsg.equipment.model.equipment.PartElement;
import ru.nabokovsg.equipment.model.library.ElementLibrary;
import ru.nabokovsg.equipment.model.library.EquipmentLibrary;
import ru.nabokovsg.equipment.model.library.PartElementLibrary;
import ru.nabokovsg.equipment.repository.equipment.ElementRepository;
import ru.nabokovsg.equipment.repository.equipment.EquipmentRepository;
import ru.nabokovsg.equipment.repository.equipment.PartElementRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SynchronizingServiceImpl implements SynchronizingService {

    private final EquipmentRepository equipmentRepository;
    private final ElementRepository elementRepository;
    private final PartElementRepository partElementRepository;
    private final SynchronizingMapper mapper;

    @Override
    public void updateEquipmentName(EquipmentLibrary equipmentLibrary) {
        Set<Equipment> equipments = equipmentRepository.findAllByEquipmentLibraryId(equipmentLibrary.getId());
        if (!equipments.isEmpty()) {
            equipments.forEach(equipment -> mapper.updateEquipmentName(equipment, equipmentLibrary));
            equipmentRepository.saveAll(equipments);
        }
    }

    @Override
    public void updateElementName(ElementLibrary elementLibrary) {
        Set<Element> elements = elementRepository.findAllByElementLibraryId(elementLibrary.getId());
       if (!elements.isEmpty()) {
           elements.forEach(element -> mapper.updateElementName(element, elementLibrary.getElementName()));
           elementRepository.saveAll(elements);
       }
    }

    @Override
    public void updatePartElementName(PartElementLibrary partElementLibrary) {
        Set<PartElement> partElements =
                                        partElementRepository.findAllByPartElementLibraryId(partElementLibrary.getId());
        if (!partElements.isEmpty()) {
            partElements.forEach(partElement -> mapper.updatePartElementName(partElement
                                                                           , partElementLibrary.getPartElementName()));
            partElementRepository.saveAll(partElements);
        }
    }
}
