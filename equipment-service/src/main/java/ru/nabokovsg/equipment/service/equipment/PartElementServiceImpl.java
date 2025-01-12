package ru.nabokovsg.equipment.service.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.exceptions.BadRequestException;
import ru.nabokovsg.equipment.mapper.equipment.PartElementMapper;
import ru.nabokovsg.equipment.model.equipment.Element;
import ru.nabokovsg.equipment.model.equipment.PartElement;
import ru.nabokovsg.equipment.model.library.PartElementLibrary;
import ru.nabokovsg.equipment.repository.equipment.PartElementRepository;
import ru.nabokovsg.equipment.service.library.PartElementLibraryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PartElementServiceImpl implements PartElementService {

    private final PartElementRepository repository;
    private final PartElementMapper mapper;
    private final PartElementLibraryService libraryService;

    @Override
    public void save(Element element, Long partElementLibraryId, String standardSize) {
        PartElementLibrary partElementLibrary = libraryService.getById(partElementLibraryId);
        exists(element.getPartsElement(), partElementLibrary.getPartElementName(), standardSize);
        PartElement partElement = repository.save(mapper.mapToEquipmentPartElement(element
                                                                                          , partElementLibrary
                                                                                          , standardSize));
        if (element.getPartsElement() == null) {
            element.setPartsElement(Set.of(partElement));
            return;
        }
        element.getPartsElement().add(partElement);
    }

    @Override
    public void update(Set<PartElement> partsElement, Long partElementId, String standardSize) {
        List<PartElement> parts = new ArrayList<>(1);
        partsElement.forEach(partElement -> {
            if (Objects.equals(partElement.getId(), partElementId)) {
                mapper.mapToUpdateEquipmentPartElement(partElement, standardSize);
                parts.add(partElement);
            }
        });
        repository.save(parts.get(0));
    }

    private void exists(Set<PartElement> partsElement, String partElementName, String standardSize) {
        if (partsElement != null) {
            partsElement.forEach(partElement -> {
                if (partElement.getPartElementName().equals(partElementName)
                                                               && partElement.getStandardSize().equals(standardSize)) {
                    throw new BadRequestException(
                            String.format("PartElement partElementName=%s, standardSize=%s is found"
                                    , partElementName
                                    , standardSize));
                }
            });
        }
    }
}