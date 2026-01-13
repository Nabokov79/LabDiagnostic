package ru.nabokovsg.referencebooks.service_factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;
import ru.nabokovsg.referencebooks.repository.ElementLibraryRepository;
import ru.nabokovsg.referencebooks.repository.PartElementLibraryRepository;

@Service
@RequiredArgsConstructor
public class ElementNameFactoryImpl implements ElementNameFactory {

    private final ElementLibraryRepository elementRepository;
    private final PartElementLibraryRepository partElementRepository;

    @Override
    public String create(Long elementLibraryId, Long partElementLibraryId) {
        if (partElementLibraryId != null) {
            return createByPartElement(partElementLibraryId);
        }
        return createByElement(elementLibraryId);
    }

    public String createByElement(Long id) {
        return elementRepository.findNameByElementLibraryId(id)
                .orElseThrow(() -> new NotFoundException(String.format("PartElement library with id=%s not found", id)));
    }

    public String createByPartElement(Long id) {
        PartElementLibrary partElement =  partElementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("PartElement library with id=%s not found", id)));
        return String.join(", ", partElement.getElement().getName(), getPartElementName(partElement));
    }

    private String getPartElementName(PartElementLibrary partElement) {
        if (partElement.getPlace() != null) {
            return String.join("", partElement.getName(), "(", partElement.getPlace(), ")");
        }
        return partElement.getName();
    }
}