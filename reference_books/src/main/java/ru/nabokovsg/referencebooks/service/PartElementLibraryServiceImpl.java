package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.NewPartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.ResponsePartElementLibraryDto;
import ru.nabokovsg.referencebooks.dto.partElementLibrary.UpdatePartElementLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.PartElementLibraryMapper;
import ru.nabokovsg.referencebooks.model.ExceptionMassage;
import ru.nabokovsg.referencebooks.model.PartElementLibrary;
import ru.nabokovsg.referencebooks.repository.PartElementLibraryRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartElementLibraryServiceImpl implements PartElementLibraryService {

    private final PartElementLibraryRepository repository;
    private final PartElementLibraryMapper mapper;
    private final ElementLibraryService elementLibraryService;
    private final static String NO_FOUND = "Подэлемент не обнаружен";

    @Override
    public ResponsePartElementLibraryDto save(NewPartElementLibraryDto partElementDto) {
        PartElementLibrary partElement = mapper.mapToPartElementLibrary(partElementDto);
        getDuplicate(partElement, partElementDto.getElementLibraryId());
        mapper.mapToElementLibrary(partElement
                , elementLibraryService.getById(partElementDto.getElementLibraryId()));
        return mapper.mapToResponsePartElementLibraryDto(repository.save(partElement));
    }

    @Override
    public ResponsePartElementLibraryDto update(UpdatePartElementLibraryDto partElementDto) {
        PartElementLibrary partElement = getById(partElementDto.getId());
        mapper.mapToUpdatePartElementLibrary(partElement, partElementDto);
        getDuplicate(partElement, partElement.getElement().getId());
        return mapper.mapToResponsePartElementLibraryDto(repository.save(partElement));
    }

    @Override
    public ResponsePartElementLibraryDto get(Long id) {
        return mapper.mapToResponsePartElementLibraryDto(getById(id));
    }

    @Override
    public List<ResponsePartElementLibraryDto> getAll(Long elementLibraryId) {
        return repository.findAllByElementId(elementLibraryId)
                .stream()
                .sorted(Comparator.comparing(PartElementLibrary::getName))
                .map(mapper::mapToResponsePartElementLibraryDto)
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

    private void getDuplicate(PartElementLibrary partElement, Long elementLibraryId) {
        if (exists(partElement, elementLibraryId)) {
            throw new BadRequestException(String.join("", ExceptionMassage.DUPLICATE.label, partElement.getName()));
        }
    }

    private boolean exists(PartElementLibrary partElement, Long elementLibraryId) {
        if (partElement.getPlace() != null) {
           return repository.existsByElementIdAndNameAndPlace(elementLibraryId, partElement.getName(), partElement.getPlace());
        }
        return repository.existsByElementIdAndName(elementLibraryId, partElement.getName());
    }
}