package ru.nabokovsg.equipment.service.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.equipment.dto.element.NewElementDto;
import ru.nabokovsg.equipment.dto.element.ResponseElementDto;
import ru.nabokovsg.equipment.dto.element.UpdateElementDto;
import ru.nabokovsg.equipment.exceptions.BadRequestException;
import ru.nabokovsg.equipment.exceptions.NotFoundException;
import ru.nabokovsg.equipment.mapper.equipment.ElementMapper;
import ru.nabokovsg.equipment.model.equipment.Element;
import ru.nabokovsg.equipment.repository.equipment.ElementRepository;
import ru.nabokovsg.equipment.service.library.ElementLibraryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElementServiceImpl implements ElementService {

    private final ElementRepository repository;
    private final ElementMapper mapper;
    private final EquipmentService equipmentService;
    private final PartElementService partElementService;
    private final ElementLibraryService libraryService;
    private final StandardSizeStringBuilder standardSizeStringBuilder;

    @Override
    public ResponseElementDto save(NewElementDto elementDto) {
        String standardSize = standardSizeStringBuilder.convertToString(mapper.mapToStandardSize(elementDto));
        Element element = getByPredicate(elementDto, standardSize);
        if (element == null) {
            element = mapper.mapToElement(libraryService.getById(elementDto.getElementLibraryId())
                                        , equipmentService.getById(elementDto.getEquipmentId()));
            if (elementDto.getPartElementLibraryId() == null) {
                mapper.mapWithStandardSize(element, standardSize);
            }
            element = repository.save(element);
        } else if (elementDto.getPartElementLibraryId() == null){
            throw new BadRequestException(String.format("Equipment element: %s; is found", elementDto));
        }
        if (elementDto.getPartElementLibraryId() != null) {
            partElementService.save(element, elementDto.getPartElementLibraryId(), standardSize);
        }
        return mapper.mapToResponseEquipmentElementDto(element);
    }

    @Override
    public ResponseElementDto update(UpdateElementDto elementDto) {
        Element element = get(elementDto.getId());
        String standardSize = standardSizeStringBuilder.convertToString(mapper.mapToUpdateStandardSize(elementDto));
        if (elementDto.getPartElementId() == null) {
            mapper.mapToUpdateElement(element
                              , standardSizeStringBuilder.convertToString(mapper.mapToUpdateStandardSize(elementDto)));
        } else {
            partElementService.update(element.getPartsElement(), elementDto.getPartElementId(), standardSize);
        }
        return mapper.mapToResponseEquipmentElementDto(repository.save(element));
    }

    @Override
    public Element get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Equipment element with id=%s not found", id)));
    }

    @Override
    public List<ResponseElementDto> getAll(Long equipmentId) {
        return repository.findByEquipmentId(equipmentId)
                .stream()
                .map(mapper::mapToResponseEquipmentElementDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Equipment element with id=%s not found for delete", id));
    }

    private Element getByPredicate(NewElementDto elementDto, String standardSize) {
        if (elementDto.getPartElementLibraryId() == null) {
            return repository.findByEquipmentIdAndElementLibraryIdAndStandardSize(elementDto.getEquipmentId()
                                                                                , elementDto.getElementLibraryId()
                                                                                , standardSize);
        } else {
            return repository.findByEquipmentIdAndElementLibraryId(elementDto.getEquipmentId()
                                                                 , elementDto.getElementLibraryId());
        }
    }
}