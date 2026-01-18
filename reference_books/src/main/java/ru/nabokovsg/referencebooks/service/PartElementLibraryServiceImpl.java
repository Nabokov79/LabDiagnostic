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
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;
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
    private final static String NO_FOUND = "Подэлемент не обнаружен";

    @Override
    public ResponseShortPartElementLibraryDto save(NewPartElementLibraryDto partElementDto) {
        String fullName = getFullName(partElementDto.getName(), partElementDto.getPlace());
        exists(null, partElementDto.getElementLibraryId(), fullName);
        PartElementLibrary partElement = mapper.mapToPartElementLibrary(partElementDto, fullName
                , factory.createDimensions(partElementDto.getDiameter(), partElementDto.getLength(), partElementDto.getHeight(), partElementDto.getWidth())
                , factory.createStandardSize(partElementDto.getDiameterSize(), partElementDto.getThicknessSize()));
        mapper.mapToElementLibrary(partElement
                , elementLibraryService.getById(partElementDto.getElementLibraryId()));
        return mapper.mapToResponseShortPartElementLibraryDto(repository.save(partElement));
    }

    @Override
    public ResponseShortPartElementLibraryDto update(UpdatePartElementLibraryDto partElementDto) {
        String fullName = getFullName(partElementDto.getName(), partElementDto.getPlace());
        exists(partElementDto.getId(), partElementDto.getElementLibraryId(), fullName);
        PartElementLibrary partElement = getById(partElementDto.getId());
        mapper.mapToUpdatePartElementLibrary(partElement, partElementDto, fullName
                , factory.createDimensions(partElementDto.getDiameter(), partElementDto.getLength(), partElementDto.getHeight(), partElementDto.getWidth())
                , factory.createStandardSize(partElementDto.getDiameterSize(), partElementDto.getThicknessSize()));
        return mapper.mapToResponseShortPartElementLibraryDto(repository.save(partElement));
    }

    @Override
    public ResponsePartElementLibraryDto get(Long id) {
        return mapper.mapToResponsePartElementLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortPartElementLibraryDto> getAll(Long id, String name) {
        Set<PartElementLibrary> partsElement = repository.findAllByElementIdOrderByFullName(id);
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
        throw new NotFoundException(NO_FOUND);
    }

    private PartElementLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NO_FOUND));
    }

    private void exists(Long id, Long elementLibraryId, String fullName) {
        boolean exists = false;
        if (id == null) {
            exists = repository.existsByElementIdAndFullName(elementLibraryId, fullName);
        } else {
            Long partId = repository.findIdByElementIdAndFullName(elementLibraryId, fullName);
            if (partId != null) {
                exists = !Objects.equals(id, partId);
            }
        }
        if (exists) {
            throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label, fullName));
        }
    }
    private String getFullName(String name, String place) {
        if (place != null) {
            return String.join(" ", name, String.join("", "(", place, ")"));
        }
        return name;
    }
}