package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.elementLibrary.NewElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseShortElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.UpdateElementLibraryDto;
import ru.nabokovsg.referencebooks.model.ElementLibrary;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.ElementLibraryMapper;
import ru.nabokovsg.referencebooks.model_enum.BadRequestExceptionMassage;
import ru.nabokovsg.referencebooks.model_enum.NotFoundExceptionMassage;
import ru.nabokovsg.referencebooks.repository.ElementLibraryRepository;
import ru.nabokovsg.referencebooks.service_factory.ElementNameFactory;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElementLibraryServiceImpl implements ElementLibraryService {

    private final ElementLibraryRepository repository;
    private final ElementLibraryMapper mapper;
    private final EquipmentLibraryService equipmentLibraryService;
    private final ElementNameFactory factory;

    @Override
    public ResponseShortElementLibraryDto save(NewElementLibraryDto elementDto) {
        exists(null, elementDto.getEquipmentLibraryId(), elementDto.getName());
        return mapper.mapToResponseShortElementLibraryDto(
                repository.save(mapper.mapToElementLibrary(elementDto
                        , equipmentLibraryService.getById(elementDto.getEquipmentLibraryId())
                        , factory.createDimensions(elementDto.getDiameter(), elementDto.getLength(), elementDto.getHeight(), elementDto.getWidth())
                        , factory.createStandardSize(elementDto.getDiameterSize(), elementDto.getThicknessSize()))));
    }

    @Override
    public ResponseShortElementLibraryDto update(UpdateElementLibraryDto elementDto) {
        ElementLibrary elementLibrary = getById(elementDto.getId());
        mapper.mapToUpdateElementLibrary(elementLibrary, elementDto
                , factory.createDimensions(elementDto.getDiameter(), elementDto.getLength(), elementDto.getHeight(), elementDto.getWidth())
                , factory.createStandardSize(elementDto.getDiameterSize(), elementDto.getThicknessSize()));
        exists(elementLibrary.getId(), elementLibrary.getEquipment().getId(), elementLibrary.getName());
        return mapper.mapToResponseShortElementLibraryDto(repository.save(elementLibrary));
    }

    @Override
    public ResponseElementLibraryDto get(Long id) {
        return mapper.mapToResponseElementLibraryDto(getById(id));
    }


    @Override
    public List<ResponseShortElementLibraryDto> getAll(Long id, String name) {
        Set<ElementLibrary> elements = repository.findAllByEquipmentIdOrderByName(id);
        if (name != null) {
            final String elementName = name.toLowerCase();
            elements = elements.stream()
                                .filter(element -> element.getName().toLowerCase().contains(elementName))
                                .collect(Collectors.toSet());
        }
        return elements.stream()
                       .map(mapper::mapToResponseShortElementLibraryDto)
                       .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(NotFoundExceptionMassage.ELEMENT.label);
    }

    @Override
    public ElementLibrary getById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundExceptionMassage.ELEMENT.label));
    }

    private void exists(Long id, Long equipmentLibraryId, String name) {
        boolean exists = false;
        if (id == null) {
            exists = repository.existsByEquipmentIdAndName(equipmentLibraryId, name);
        } else {
            Long elementId = repository.findByEquipmentIdAndName(equipmentLibraryId, name);
            if (elementId != null) {
                exists = !Objects.equals(id, elementId);
            }
        }
        if (exists) {
            throw new BadRequestException(String.join("", BadRequestExceptionMassage.DUPLICATE.label, name));
        }
    }
}