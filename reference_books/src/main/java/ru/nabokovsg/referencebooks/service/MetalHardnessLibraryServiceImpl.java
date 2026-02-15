package ru.nabokovsg.referencebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.NewMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.ResponseShortMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.dto.metalHardnessLibrary.UpdateMetalHardnessLibraryDto;
import ru.nabokovsg.referencebooks.exceptions.BadRequestException;
import ru.nabokovsg.referencebooks.exceptions.NotFoundException;
import ru.nabokovsg.referencebooks.mapper.MetalHardnessLibraryMapper;
import ru.nabokovsg.referencebooks.model.*;
import ru.nabokovsg.referencebooks.repository.MetalHardnessLibraryRepository;
import ru.nabokovsg.referencebooks.search.SearchService;
import ru.nabokovsg.referencebooks.toStringService.ToStringService;
import ru.nabokovsg.referencebooks.validators.SizeAcceptableCheckingValidator;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetalHardnessLibraryServiceImpl implements MetalHardnessLibraryService {

     private final MetalHardnessLibraryRepository repository;
    private final MetalHardnessLibraryMapper mapper;
    private final SizeAcceptableCheckingValidator validator;
    private final RegulatoryDocumentationLibraryService documentationService;
    private final ToStringService toString;
    private final SearchService searchService;
    private final ElementLibraryService elementService;
    private final PartElementLibraryService partElementService;
    private final static String MASSAGE = "Допустимое значение твердости металла не обнаружено.";

    @Override
    public ResponseShortMetalHardnessLibraryDto save(NewMetalHardnessLibraryDto hardnessDto) {
        MetalHardnessLibrary metalHardness = mapper.mapToMetalHardnessLibrary(hardnessDto);
        build(metalHardness, hardnessDto.getElementId(), hardnessDto.getPartElementId(), hardnessDto.getDocumentationId());
        return getResponseShortMetalHardnessLibraryDto(repository.save(metalHardness));
    }

    @Override
    public ResponseShortMetalHardnessLibraryDto update(UpdateMetalHardnessLibraryDto hardnessDto) {
        MetalHardnessLibrary metalHardness = getById(hardnessDto.getId());
        mapper.mapToUpdateMetalHardnessLibrary(metalHardness, hardnessDto);
        build(metalHardness, hardnessDto.getElementId(), hardnessDto.getPartElementId(), hardnessDto.getDocumentationId());
        return getResponseShortMetalHardnessLibraryDto(repository.save(metalHardness));
    }

    @Override
    public ResponseMetalHardnessLibraryDto get(Long id) {
        return getResponseMetalHardnessLibraryDto(getById(id));
    }

    @Override
    public List<ResponseShortMetalHardnessLibraryDto> getAll(String search) {
        Set<MetalHardnessLibrary> metalsHardness = repository.findAllOrderByEquipmentFullName();
        if (search != null) {
            metalsHardness = metalsHardness.stream()
                    .filter(metalHardness -> searchService.search(search, getSearchList(metalHardness)))
                    .collect(Collectors.toSet());
        }
        return  metalsHardness.stream()
                              .map(this::getResponseShortMetalHardnessLibraryDto)
                              .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(MASSAGE);
    }

    private MetalHardnessLibrary getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(MASSAGE));
    }

    private void build(MetalHardnessLibrary metalHardness, Long elementId, Long partElementId, Long documentationId) {
        validate(metalHardness);
        if (partElementId != null) {
            PartElementLibrary partElement = partElementService.getById(partElementId);
            mapper.mapWithPartElement(metalHardness
                                , partElement.getElement()
                                , partElement
                                , documentationService.getById(documentationId)
                                , toString.getStandardSize(metalHardness.getDiameter(), metalHardness.getThickness()));
        } else {
            mapper.mapWithElement(metalHardness
                                , elementService.getById(elementId)
                                , documentationService.getById(documentationId)
                                , toString.getStandardSize(metalHardness.getDiameter(), metalHardness.getThickness()));
        }
        exists(metalHardness);
    }

    private void exists(MetalHardnessLibrary metalHardness) {
        MetalHardnessLibrary duplicate;
        if (metalHardness.getPartElement() != null) {
            duplicate = repository.findByPartElementIdAndDocumentationId(metalHardness.getPartElement().getId()
                                                                       , metalHardness.getDocumentation().getId());
        } else {
            duplicate = repository.findByElementIdAndDocumentationId(metalHardness.getElement().getId()
                                                                   , metalHardness.getDocumentation().getId());
        }
        if (duplicate != null && (metalHardness.getId() == null || !Objects.equals(duplicate.getId(), metalHardness.getId()))) {
            throw new BadRequestException(
                    String.join("", ExceptionMassage.DUPLICATE.label, getElementFullName(metalHardness)));
        }
    }

    private ResponseMetalHardnessLibraryDto getResponseMetalHardnessLibraryDto(MetalHardnessLibrary metalHardness) {
        if (metalHardness.getPartElement() != null) {
            return mapper.mapToResponseMetalHardnessDtoByPartElement(metalHardness);
        }
        return mapper.mapToResponseMetalHardnessDtoByElement(metalHardness);
    }

    private ResponseShortMetalHardnessLibraryDto getResponseShortMetalHardnessLibraryDto(MetalHardnessLibrary metalHardness) {
        if (metalHardness.getPartElement() != null) {
            return mapper.mapToResponseShortMetalHardnessLibraryDtoByPartElement(metalHardness);
        }
        return mapper.mapToResponseShortMetalHardnessLibraryDtoByElement(metalHardness);
    }

    private String getElementFullName(MetalHardnessLibrary metalHardness) {
        if (metalHardness.getPartElement() != null) {
            return metalHardness.getPartElement().getPartElementFullName();
        }
        return metalHardness.getElement().getName();
    }

    private List<String> getSearchList(MetalHardnessLibrary metalHardness) {
        if (metalHardness.getPartElement() != null) {
            return List.of(metalHardness.getPartElement().getElement().getEquipment().getEquipmentFullName()
                         , metalHardness.getPartElement().getName()
                         , metalHardness.getDocumentation().getDocument());
        }
        return List.of(metalHardness.getElement().getEquipment().getEquipmentFullName()
                     , metalHardness.getElement().getName()
                     , metalHardness.getDocumentation().getDocument());
    }

    private void validate(MetalHardnessLibrary metalHardness) {
        validator.validateStandardSize(metalHardness.getDiameter(), metalHardness.getThickness());
        validator.validateAcceptableHardness(metalHardness.getMinAcceptableHardness(), metalHardness.getMaxAcceptableHardness());
    }
}