package ru.nabokovsg.equipment.service.equipment;

import ru.nabokovsg.equipment.model.equipment.Element;
import ru.nabokovsg.equipment.model.equipment.PartElement;

import java.util.Set;

public interface PartElementService {

    void save(Element element, Long partElementLibraryId, String standardSize);

    void update(Set<PartElement> partsElement, Long partElementId, String standardSize);
}