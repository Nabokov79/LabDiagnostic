package ru.nabokovsg.equipment.service.library;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.mapper.library.EquipmentCopyingMapper;
import ru.nabokovsg.equipment.model.library.ElementLibrary;
import ru.nabokovsg.equipment.model.library.EquipmentLibrary;
import ru.nabokovsg.equipment.model.library.PartElementLibrary;
import ru.nabokovsg.equipment.repository.library.ElementLibraryRepository;
import ru.nabokovsg.equipment.repository.library.PartElementLibraryRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EquipmentCopyingServiceImpl implements EquipmentCopyingService {
    private final EquipmentCopyingMapper mapper;
    private final ElementLibraryRepository elementLibraryRepository;
    private final PartElementLibraryRepository partElementLibraryRepository;

    @Override
    public void copy(EquipmentLibrary equipment, Long equipmentLibraryId) {
        Map<String, Set<PartElementLibrary>> partsElement = new HashMap<>();
        List<ElementLibrary> elementsCopies = elementLibraryRepository.findAllByEquipmentLibraryId(equipmentLibraryId)
                                              .stream()
                                              .map(element -> {
                                                  partsElement.put(element.getElementName(), element.getPartsElement());
                                                        return mapper.mapToCopyElementLibrary(element, equipment);
                                               })
                                             .toList();
        elementsCopies = elementLibraryRepository.saveAll(elementsCopies);
        elementsCopies.forEach(element -> copyPartElement(element, partsElement.get(element.getElementName())));
        equipment.setElements(new HashSet<>(elementsCopies));
    }

    private void copyPartElement(ElementLibrary element, Set<PartElementLibrary> partsElement) {
        if (partsElement != null) {
            List<PartElementLibrary> partsElementCopies = partsElement.stream()
                    .map(partElementLibrary -> mapper.mapToCopyPartElementLibrary(partElementLibrary, element))
                    .toList();
            element.setPartsElement(new HashSet<>(partElementLibraryRepository.saveAll(partsElementCopies)));
        }
    }
}