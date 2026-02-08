package ru.nabokovsg.referencebooks.service_factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.model.ElementLibrary;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;
import ru.nabokovsg.referencebooks.service.ElementLibraryService;
import ru.nabokovsg.referencebooks.service.PartElementLibraryService;
import ru.nabokovsg.referencebooks.toStringService.ToStringService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EquipmentInformationBuilderServiceImpl implements EquipmentInformationBuilderService {
    private final ElementLibraryService elementService;
    private final PartElementLibraryService partElementService;
    private final ToStringService toString;

    @Override
    public Map<String, String> getByElement(Long elementLibraryId) {
        ElementLibrary element = elementService.getById(elementLibraryId);
        return Map.of(toString.getEquipmentLibraryFullName(element.getEquipment())
                                                         , String.join(", ", element.getName()));
    }

    @Override
    public Map<String, String> getByPartElement(Long partElementLibraryId) {
        PartElementLibrary partElement = partElementService.getById(partElementLibraryId);
        return Map.of(toString.getEquipmentLibraryFullName(partElement.getElement().getEquipment())
                           , String.join(", ", partElement.getElement().getName(), partElement.getFullName()));
    }
}