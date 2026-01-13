package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.elementLibrary.NewElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.ResponseElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.elementLibrary.UpdateElementLibraryDto;
import ru.nabokovsg.referencebooks.model.ElementLibrary;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.ElementLibraryMapper;
import ru.nabokovsg.referencebooks.model.EquipmentLibrary;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.repository.ElementLibraryRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ElementLibraryServiceImpl implements ElementLibraryService {

    private final ElementLibraryRepository repository;
    private final ElementLibraryMapper mapper;
    private final EquipmentLibraryService equipmentLibraryService;
    private final static String NO_FOUND = "Элемент не обнаружен";

    @Override
    public ResponseElementLibraryDto save(NewElementLibraryDto elementDto) {
        exists(elementDto.getEquipmentLibraryId(), elementDto.getName());
        return mapper.mapToResponseElementLibraryDto(
                repository.save(mapper.mapToElementLibrary(elementDto
                        , equipmentLibraryService.getById(elementDto.getEquipmentLibraryId()))));
    }

    @Override
    public ResponseElementLibraryDto update(UpdateElementLibraryDto elementDto) {
        ElementLibrary elementLibrary = getById(elementDto.getId());
        exists(elementLibrary.getEquipment().getId(), elementDto.getName());
        mapper.mapToUpdateElementLibrary(elementLibrary, elementDto);
        return mapper.mapToResponseElementLibraryDto(repository.save(elementLibrary));
    }

    @Override
    public ResponseElementLibraryDto get(Long id) {
        return mapper.mapToResponseElementLibraryDto(getById(id));
    }

    private void exists(Long equipmentLibraryId, String name) {
        if (repository.existsByEquipmentIdAndName(equipmentLibraryId, name)) {
            throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label, name));
        }
    }

    @Override
    public List<ResponseElementLibraryDto> getAll(Long equipmentLibraryId) {
        return repository.findAllByEquipmentId(equipmentLibraryId)
                         .stream()
                         .sorted(Comparator.comparing(ElementLibrary::getName))
                         .map(mapper::mapToResponseElementLibraryDto)
                         .toList();
    }

    @Override
    public List<ResponseElementLibraryDto> copy(Long equipmentLibraryId) {
        EquipmentLibrary equipment = equipmentLibraryService.getById(equipmentLibraryId);
        return repository.saveAll(repository.findAllByEquipmentId(equipmentLibraryId)
                                             .stream()
                                             .map(element -> mapper.mapToCopyElementLibrary(element, equipment))
                                             .toList())
                         .stream()
                         .sorted(Comparator.comparing(ElementLibrary::getName))
                         .map(mapper::mapToResponseElementLibraryDto)
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

    @Override
    public ElementLibrary getById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NO_FOUND));
    }
}