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
    private final static String DELIMETER = "x";

    @Override
    public String create(Long elementLibraryId, Long partElementLibraryId) {
        if (partElementLibraryId != null) {
            return createByPartElement(partElementLibraryId);
        }
        return createByElement(elementLibraryId);
    }

    @Override
    public String createDimensions(Integer diameter, Integer length, Integer height, Integer width) {
        String dimensions = null;
        if (length != null) {
            dimensions = String.valueOf(length);
        }
        if (width!= null) {
            if (dimensions != null) {
                dimensions = String.join(DELIMETER, dimensions, String.valueOf(width));
            } else {
                dimensions = String.valueOf(width);
            }
        }
        if (height != null) {
            if (dimensions != null) {
                dimensions = String.join(DELIMETER, dimensions, String.valueOf(height));
            } else {
                dimensions = String.valueOf(height);
            }
        }
        if (diameter != null) {
            if (dimensions != null) {
                dimensions = String.join(DELIMETER, dimensions, String.valueOf(diameter));
            } else {
                dimensions = String.valueOf(diameter);
            }
        }
        if (dimensions != null) {
            return String.join(" ", dimensions, createLetters(diameter, length, height, width));
        }
        return dimensions;
    }

    public String createLetters(Integer diameter, Integer length, Integer height, Integer width) {
        String letters = null;
        if (length != null) {
            letters = "L";
        }
        if (width!= null) {
            if (letters != null) {
                letters = String.join(DELIMETER, letters, "В");
            } else {
                letters = "В";
            }
        }
        if (height != null) {
            if (letters != null) {
                letters = String.join(DELIMETER, letters, "Н");
            } else {
                letters = "Н";
            }
        }
        if (diameter != null) {
            if (letters != null) {
                letters = String.join(DELIMETER, letters, "D");
            } else {
                letters = "D";
            }
        }
        return String.join("", "(", letters, ")");
    }

    @Override
    public String createStandardSize(Integer diameterSize, Double thicknessSize) {
        String standardSize = null;
        if (diameterSize != null) {
            standardSize = String.valueOf(diameterSize);
        }
        if (thicknessSize != null) {
            if (standardSize != null) {
                standardSize = String.join(DELIMETER, standardSize, String.valueOf(thicknessSize));
            } else {
                standardSize = String.valueOf(thicknessSize);
            }
        }
        return standardSize;
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