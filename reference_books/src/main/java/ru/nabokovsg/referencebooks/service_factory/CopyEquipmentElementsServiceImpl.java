package ru.nabokovsg.referencebooks.service_factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseShortElementLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.mapper.CopyEquipmentElementsMapper;
import ru.nabokovsg.referencebooks.mapper.ElementLibraryMapper;
import ru.nabokovsg.referencebooks.model.ElementLibrary;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;
import ru.nabokovsg.referencebooks.repository.ElementLibraryRepository;
import ru.nabokovsg.referencebooks.repository.PartElementLibraryRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CopyEquipmentElementsServiceImpl implements CopyEquipmentElementsService {

    private final CopyEquipmentElementsMapper mapper;
    private final ElementLibraryMapper elementMapper;
    private final ElementLibraryRepository elementRepository;
    private final PartElementLibraryRepository partElementRepository;

    @Override
    public List<ResponseShortElementLibraryDto> copyElements(EquipmentLibrary equipment, EquipmentLibrary copyEquipment) {
        if (!equipment.getElements().isEmpty()) {
            throw new BadRequestException("Все элементы скопированы.");
        }
        Map<String, Set<PartElementLibrary>> partsElement = new HashMap<>();
        List<ElementLibrary> elements = copyEquipment.getElements().stream()
                .map(element -> {
                    partsElement.put(element.getName(), element.getPartsElement());
                    return mapper.mapToCopyElementLibrary(element, equipment);
                })
                .toList();
        elements = elementRepository.saveAll(elements);
        copyPartElement(elements.stream().collect(Collectors.toMap(element -> element, element -> partsElement.get(element.getName()))));
        return elements.stream().map(elementMapper::mapToResponseShortElementLibraryDto).toList();
    }

    public void copyPartElement(Map<ElementLibrary, Set<PartElementLibrary>> partsElement) {
       Set<PartElementLibrary> parts = new HashSet<>();
       partsElement.forEach((k,v) -> v.forEach(part -> parts.add(mapper.mapToCopyPartElementLibrary(part, k))));
       partElementRepository.saveAll(parts);
    }
}