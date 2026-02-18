package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.NewPartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.ResponseShortPartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.ResponsePartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.UpdatePartElementLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.PartElementLibraryMapper;
import ru.nabokovsg.referencebooks.model.ElementLibrary;
import ru.nabokovsg.referencebooks.model_enum.BadRequestExceptionMassage;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;
import ru.nabokovsg.referencebooks.model_enum.NotFoundExceptionMassage;
import ru.nabokovsg.referencebooks.repository.PartElementLibraryRepository;
import ru.nabokovsg.referencebooks.service_factory.ElementNameFactory;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartElementLibraryServiceImpl implements PartElementLibraryService {

    private final PartElementLibraryRepository repository;
    private final PartElementLibraryMapper mapper;
    private final ElementLibraryService elementLibraryService;
    private final ElementNameFactory factory;

    @Override
    public ResponseShortPartElementLibraryDto save(NewPartElementLibraryDto partElementDto) {
        PartElementLibrary partElement = mapper.mapToPartElementLibrary(partElementDto);
        build(partElement, partElementDto.getElementId());
        return mapper.mapToResponseShortPartElementLibraryDto(repository.save(partElement));
    }

    @Override
    public ResponseShortPartElementLibraryDto update(UpdatePartElementLibraryDto partElementDto) {
        PartElementLibrary partElement = getById(partElementDto.getId());
        mapper.mapToUpdatePartElementLibrary(partElement, partElementDto);
        build(partElement, partElementDto.getElementId());
        return mapper.mapToResponseShortPartElementLibraryDto(repository.save(partElement));
    }

    @Override
    public ResponsePartElementLibraryDto get(Long id) {
        return mapper.mapToResponsePartElementLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortPartElementLibraryDto> getAll(Long id, String name) {
        Set<PartElementLibrary> partsElement = repository.findAllByElementIdOrderByFullNameDesc(id);
        if (name != null) {
            final String fullName = name.toLowerCase();
            partsElement = partsElement.stream()
                    .filter(part -> part.getFullName().toLowerCase().contains(fullName))
                    .collect(Collectors.toSet());
        }
        return partsElement.stream()
                .map(mapper::mapToResponseShortPartElementLibraryDto)
                .toList();

    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(NotFoundExceptionMassage.PART_ELEMENT.label);
    }

    @Override
    public PartElementLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundExceptionMassage.PART_ELEMENT.label));
    }

    private void build(PartElementLibrary partElement, Long elementId) {
        String fullName = getFullName(partElement.getName(), partElement.getPlace());
        ElementLibrary element = elementLibraryService.getById(elementId);
        mapper.mapWithFields(partElement
                , element
                , String.join(", ", element.getName(), fullName)
                , fullName
                , factory.createDimensions(partElement.getDiameter(), partElement.getLength(), partElement.getHeight(), partElement.getWidth())
                , factory.createStandardSize(partElement.getDiameterSize(), partElement.getThicknessSize()));
        exists(partElement.getId(), partElement.getElement(), partElement.getFullName());
    }

    private void exists(Long id, ElementLibrary element, String fullName) {
        boolean exists = false;
        if (id == null) {
            exists = repository.existsByElementAndFullName(element, fullName);
        } else {
            Long partId = repository.findIdByElementAndFullName(element, fullName);
            if (partId != null) {
                exists = !Objects.equals(id, partId);
            }
        }
        if (exists) {
            throw new BadRequestException(String.join("", BadRequestExceptionMassage.DUPLICATE.label, fullName));
        }
    }
    private String getFullName(String name, String place) {
        if (place != null) {
            return String.join(" ", name, String.join("", "(", place, ")"));
        }
        return name;
    }
}